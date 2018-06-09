package com.pargroup.controller;

import java.util.concurrent.TimeUnit;
import com.pargroup.event.ChipPlacedEvent;
import com.pargroup.event.EventManager;
import com.pargroup.event.GameEvent;
import com.pargroup.event.PlaceChipRequestEvent;
import com.pargroup.event.StopGameEvent;
import com.pargroup.event.StopRequestEvent;
import com.pargroup.event.listener.GameListener;
import com.pargroup.model.Board;
import com.pargroup.model.Chip;
import com.pargroup.model.ChipColour;
import com.pargroup.resources.ConfigsLoader;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController implements GameListener {

  private static final long TICK_RATE = TimeUnit.MILLISECONDS.toNanos(30);

  private EventManager eventManager;

  private Board board;

  private long accumulatedTime;
  private long prevTime;

  private boolean running;
  private boolean paused;

  public void initialize() {

    eventManager = new EventManager();

    eventManager.addGameListener(PlaceChipRequestEvent.class, this);
    eventManager.addGameListener(StopRequestEvent.class, this);

    board = new Board(ConfigsLoader.getBoardConfig());

    running = true;
    paused = false;

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

    if (!paused) {
      eventManager.processEvents();
    }

  }

  /**
   * @see com.pargroup.event.listener.GameListener#onEvent(com.pargroup.event.GameEvent)
   */
  @Override
  public void onEvent(GameEvent e) {

    if (e instanceof PlaceChipRequestEvent) {

      PlaceChipRequestEvent placeChipRequestEvent = (PlaceChipRequestEvent) e;

      Chip mockChip = new Chip();
      mockChip.setColour(ChipColour.RED);

      placeChip(mockChip, placeChipRequestEvent.getColumn());

    } else if (e instanceof StopRequestEvent) {

      // Could have some logic to decide whether we should stop right now or not (e.g. prompt to
      // save or something).
      eventManager.addEvent(new StopGameEvent());

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
    eventManager.addEvent(new ChipPlacedEvent(chip, column, row));

  }

  public void terminate() {
    running = false;
  }

  /**
   * @param paused the paused to set
   */
  public void setPaused(boolean paused) {
    this.paused = paused;
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

}
