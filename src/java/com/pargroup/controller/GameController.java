package com.pargroup.controller;

import java.util.concurrent.TimeUnit;
import com.pargroup.event.CheckBoardEvent;
import com.pargroup.event.ChipPlacedEvent;
import com.pargroup.event.DrawEvent;
import com.pargroup.event.EventManager;
import com.pargroup.event.PlaceChipRequestEvent;
import com.pargroup.event.PlayerWinEvent;
import com.pargroup.event.RequestEvent;
import com.pargroup.event.ResolutionEvent;
import com.pargroup.event.RestartGameEvent;
import com.pargroup.event.StopGameEvent;
import com.pargroup.event.StopRequestEvent;
import com.pargroup.event.listener.RequestListener;
import com.pargroup.event.listener.ResolutionListener;
import com.pargroup.model.Board;
import com.pargroup.model.Chip;
import com.pargroup.model.Player;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController implements RequestListener, ResolutionListener {

  private static final long TICK_RATE = TimeUnit.MILLISECONDS.toNanos(30);
  private static final int CHIPS_TO_WIN = 4;

  private EventManager eventManager;

  private Board board;

  private Player[] players;
  private Player currentPlayer;

  private int turn;

  private long accumulatedTime;
  private long prevTime;

  private boolean running;

  public void initialize() {

    eventManager = new EventManager();

    eventManager.addRequestListener(PlaceChipRequestEvent.class, this);
    eventManager.addRequestListener(StopRequestEvent.class, this);
    eventManager.addRequestListener(RestartGameEvent.class, this);
    eventManager.addRequestListener(CheckBoardEvent.class, this);

    eventManager.addResolutionListener(ChipPlacedEvent.class, this);

    board = new Board();

    players =
        new Player[] {new Player("#0000FFFF", "Player 1"), new Player("#FF0000FF", "Player 2")};

    turn = 0;

    currentPlayer = players[0];

    running = true;

    Thread gameThread = new Thread(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {

        accumulatedTime = 0;
        prevTime = System.nanoTime();

        while (running) {

          long currentTime = System.nanoTime();
          long deltaTime = currentTime - prevTime;
          prevTime = currentTime;

          accumulatedTime += deltaTime;

          while (accumulatedTime >= TICK_RATE) {
            accumulatedTime -= TICK_RATE;
            GameController.this.tick();
          }

        }

      }
    }, "Game Thread");

    gameThread.start();

  }

  private synchronized void tick() {

    eventManager.processEvents();

  }

  /**
   * @see com.pargroup.event.listener.RequestListener#onEvent(com.pargroup.event.RequestEvent)
   */
  @Override
  public void onEvent(RequestEvent e) {

    if (e instanceof PlaceChipRequestEvent) {

      PlaceChipRequestEvent placeChipRequestEvent = (PlaceChipRequestEvent) e;

      Chip chip = new Chip();
      chip.setOwner(currentPlayer);
      chip.setColour(currentPlayer.getColour());

      placeChip(chip, placeChipRequestEvent.getColumn());

    } else if (e instanceof CheckBoardEvent) {

      CheckBoardEvent checkBoardEvent = (CheckBoardEvent) e;

      Chip chip = checkBoardEvent.getLastChipPlaced();

      if (checkWin(board.getChips(), chip)) {
        eventManager.addEvent(new PlayerWinEvent(chip.getOwner()));
      } else if (isBoardFull(board.getChips())) {
        eventManager.addEvent(new DrawEvent());
      }

    } else if (e instanceof RestartGameEvent) {

      board.clear();
      turn = 0;
      currentPlayer = players[turn];

    } else if (e instanceof StopRequestEvent) {

      // Could have some logic to decide whether we should stop right now or not (e.g. prompt to
      // save or something).
      eventManager.addEvent(new StopGameEvent());

    }

  }

  /**
   * @see com.pargroup.event.listener.ResolutionListener#onEvent(com.pargroup.event.ResolutionEvent)
   */
  @Override
  public void onEvent(ResolutionEvent e) {

    if (e instanceof ChipPlacedEvent) {

      turn++;

      currentPlayer = players[turn % players.length];

    }

  }

  private void placeChip(Chip chip, int column) {

    Chip[][] chips = board.getChips();

    int row = 0;

    for (; row < chips.length && chips[row][column] == null; row++);

    if (row <= 0) {
      return;
    }

    row--;

    chips[row][column] = chip;

    chip.setRow(row);
    chip.setColumn(column);

    eventManager.addEvent(new ChipPlacedEvent(chip, column, row));// TODO: refactor chipplaced event
    // TODO: stop using TODOs

  }

  private boolean isBoardFull(Chip[][] chips) {

    for (int row = 0; row < chips.length; row++) {
      for (int column = 0; column < chips[row].length; column++) {
        if (chips[row][column] == null) {
          return false;
        }
      }
    }

    return true;

  }

  private boolean checkWin(Chip[][] chips, Chip lastChipPlaced) {

    final int column = lastChipPlaced.getColumn();
    final int row = lastChipPlaced.getRow();

    return checkHorizontalWin(chips, lastChipPlaced, column, row)
        || checkVerticalWin(chips, lastChipPlaced, column, row)
        || checkRightDiagonalWin(chips, lastChipPlaced, column, row)
        || checkLeftDiagonalWin(chips, lastChipPlaced, column, row);

  }

  private boolean checkHorizontalWin(Chip[][] chips, Chip lastChipPlaced, int column, int row) {

    int currentStreak = 1;

    for (int i = 1; i < CHIPS_TO_WIN; i++) {

      int xToCheck = column + i;

      if (xToCheck >= Board.COLUMNS) {
        break;
      }

      Chip chip = chips[row][xToCheck];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    for (int i = -1; i > -CHIPS_TO_WIN; i--) {

      int xToCheck = column + i;

      if (xToCheck < 0) {
        break;
      }

      Chip chip = chips[row][xToCheck];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    return false;

  }

  private boolean checkVerticalWin(Chip[][] chips, Chip lastChipPlaced, int column, int row) {

    int currentStreak = 1;

    for (int i = 1; i < CHIPS_TO_WIN; i++) {

      int yToCheck = row + i;

      if (yToCheck >= Board.ROWS) {
        break;
      }

      Chip chip = chips[yToCheck][column];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    for (int i = -1; i > -CHIPS_TO_WIN; i--) {

      int yToCheck = row + i;

      if (yToCheck < 0) {
        break;
      }

      Chip chip = chips[yToCheck][column];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    return false;

  }

  /**
   * OOO*</br>
   * OO*O</br>
   * O*OO</br>
   * *OOO
   * 
   * @param chips
   * @param lastChipPlaced
   * @param column
   * @param row
   * @return
   */
  private boolean checkRightDiagonalWin(Chip[][] chips, Chip lastChipPlaced, int column, int row) {

    int currentStreak = 1;


    for (int i = 1; i < CHIPS_TO_WIN; i++) {

      int xToCheck = column + i;
      int yToCheck = row - i;

      if (xToCheck >= Board.COLUMNS || yToCheck < 0) {
        break;
      }

      Chip chip = chips[yToCheck][xToCheck];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }

    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    for (int i = -1; i > -CHIPS_TO_WIN; i--) {

      int xToCheck = column + i;
      int yToCheck = row - i;

      if (xToCheck < 0 || yToCheck >= Board.ROWS) {
        break;
      }

      Chip chip = chips[yToCheck][xToCheck];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    return false;

  }

  /**
   * 
   * *OOO</br>
   * O*OO</br>
   * OO*O</br>
   * OOO*
   * 
   * @param chips
   * @param lastChipPlaced
   * @param column
   * @param row
   * @return
   */
  private boolean checkLeftDiagonalWin(Chip[][] chips, Chip lastChipPlaced, int column, int row) {

    int currentStreak = 1;


    for (int i = 1; i < CHIPS_TO_WIN; i++) {

      int xToCheck = column + i;
      int yToCheck = row + i;

      if (xToCheck >= Board.COLUMNS || yToCheck >= Board.ROWS) {
        break;
      }

      Chip chip = chips[yToCheck][xToCheck];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    for (int i = -1; i > -CHIPS_TO_WIN; i--) {

      int xToCheck = column + i;
      int yToCheck = row + i;

      if (xToCheck < 0 || yToCheck < 0) {
        break;
      }

      Chip chip = chips[yToCheck][xToCheck];

      if (chip == null) {
        break;
      } else if (chip.getOwner().equals(lastChipPlaced.getOwner())) {
        currentStreak++;
      } else {
        break;
      }


    }

    if (currentStreak >= CHIPS_TO_WIN) {
      return true;
    }

    return false;

  }

  public void terminate() {
    running = false;
  }

  /**
   * @return the eventManager
   */
  public EventManager getEventManager() {
    return eventManager;
  }

  /**
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * @return the currentPlayer
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }
}
