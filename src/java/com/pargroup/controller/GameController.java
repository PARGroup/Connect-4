package com.pargroup.controller;

import java.io.Console;
import java.util.concurrent.TimeUnit;
import com.pargroup.event.ChipPlacedEvent;
import com.pargroup.event.EventManager;
import com.pargroup.event.PlaceChipRequestEvent;
import com.pargroup.event.RequestEvent;
import com.pargroup.event.ResolutionEvent;
import com.pargroup.event.StopGameEvent;
import com.pargroup.event.StopRequestEvent;
import com.pargroup.event.listener.RequestListener;
import com.pargroup.event.listener.ResolutionListener;
import com.pargroup.model.Board;
import com.pargroup.model.Chip;
import com.pargroup.model.Player;
import com.pargroup.resources.ThemeLoader;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController implements RequestListener, ResolutionListener {

  private static final long TICK_RATE = TimeUnit.MILLISECONDS.toNanos(30);

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

    eventManager.addResolutionListener(ChipPlacedEvent.class, this);

    board = new Board();

    players = new Player[] {new Player("#0000FFFF"), new Player("#FF0000FF")};

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
    eventManager.addEvent(new ChipPlacedEvent(chip, column, row));//TODO: refactor chipplaced event
    //TODO: stop using TODOs

    checkWin(board.getChips(), row, column);
  }
  
  private boolean checkWin(Chip[][] chips, int row, int column) {
	  int xmax = 7; //width
	  int ymax = 6;  //height
	  
	  /*for (int x = 0; x < chips.length; x++) {
		  for (int y = 0; y < chips[0].length; y++) {
			  System.out.print(x + " " + y + "    ");
		  }
		  System.out.println();
	  }*/
	  
	  Player lastChipOwner = null;
	  int currentStreak = 0;
	  
	  //horizontal
	  for (int x = 0; x < xmax; x++) {
		  if (chips[row][x] == null){
			  lastChipOwner = null;
			  currentStreak = 0;
			  //System.out.print(currentStreak + " ");
		  } else if (lastChipOwner == null || chips[row][x].getOwner() == lastChipOwner) {
			  System.out.println(lastChipOwner == null);
			  System.out.println(chips[row][x].getOwner() == lastChipOwner);
			  currentStreak++;
			  //System.out.print(currentStreak + " ");
			  if (currentStreak >= 4) {
				  System.out.println("Win!");
				  return true;
			  }
		  } else {
			  lastChipOwner = chips[row][x].getOwner();
			  currentStreak = 0;
			  //System.out.print(currentStreak + " ");
		  }
	  }
	  System.out.println();
	  
	  /*if (1 + checkWinRecursive(chips, row, column, 1, 0) + checkWinRecursive(chips, row, column, -1, 0) >= 4
		  || 1 + checkWinRecursive(chips, row, column, 0, 1) + checkWinRecursive(chips, row, column, 0, -1) >= 4
		  || 1 + checkWinRecursive(chips, row, column, 1, 1) + checkWinRecursive(chips, row, column, -1, -1) >= 4
		  || 1 + checkWinRecursive(chips, row, column, 1, -1) + checkWinRecursive(chips, row, column, -1, 1) >= 4) {
		  System.out.println("Win!");
	  }*/
	  
	  return false; //temp
  }
  
  private int checkWinRecursive(Chip[][] chips, int row, int column, int dirx, int diry) {
	  int xmax = 7; //width
	  int ymax = 6;  //height
	  
	  if (row + diry < 0 || row + diry >= ymax || column + dirx < 0 || column + dirx >= xmax) {
		  return 0;
	  } else if (chips[row+diry][column+dirx] == null) {
		  return 0;
	  } else if (chips[row][column].getOwner() == chips[row+diry][column+dirx].getOwner()) {
		  return 0;
	  } else {
		  return 1 + checkWinRecursive(chips, row+diry, column+dirx, dirx, diry);
	  }
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
