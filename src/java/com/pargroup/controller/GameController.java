package com.pargroup.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import com.pargroup.event.Event;
import com.pargroup.event.listener.UIListener;
import com.pargroup.model.Board;
import com.pargroup.resources.ConfigsLoader;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController {

  private static final long TICK_RATE = TimeUnit.MILLISECONDS.toNanos(30);

  private final HashMap<Class<?>, LinkedList<UIListener>> uiEventListeners =
      new HashMap<Class<?>, LinkedList<UIListener>>();
  private final Queue<Event> events = new LinkedList<Event>();

  private Board board;

  private long accumulatedTime;
  private long prevTime;

  private boolean running;
  private boolean paused;

  public void initialize() {

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

    while (!paused && !events.isEmpty()) {
      Event e = events.poll();
      processEvent(e);
    }

  }

  private void processEvent(Event e) {

  }

  public synchronized void addEvent(Event e) {
    events.add(e);
  }

  public void addUIListener(Class<?> eventClass, UIListener listener) {

    LinkedList<UIListener> listeners = uiEventListeners.get(eventClass);

    if (listeners == null) {
      listeners = new LinkedList<UIListener>();
      uiEventListeners.put(eventClass, listeners);
    }

    listeners.add(listener);

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
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

}
