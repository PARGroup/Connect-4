package com.pargroup.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import com.pargroup.event.listener.GameListener;
import com.pargroup.event.listener.Listener;
import com.pargroup.event.listener.UIListener;

/**
 * @author Rawad Aboudlal
 *
 */
public class EventManager {


  private HashMap<Class<?>, LinkedList<GameListener>> gameEventListeners =
      new HashMap<Class<?>, LinkedList<GameListener>>();
  private HashMap<Class<?>, LinkedList<UIListener>> uiEventListeners =
      new HashMap<Class<?>, LinkedList<UIListener>>();

  private Queue<Event> events = new LinkedList<Event>();

  public void addGameListener(Class<? extends GameEvent> eventClass, GameListener listener) {
    addGenericListener(gameEventListeners, eventClass, listener);
  }

  public void addUIListener(Class<? extends UIEvent> eventClass, UIListener listener) {
    addGenericListener(uiEventListeners, eventClass, listener);
  }

  private <T extends Listener> void addGenericListener(HashMap<Class<?>, LinkedList<T>> listeners,
      Class<? extends Event> eventClass, T listener) {

    LinkedList<T> listenersList = listeners.get(eventClass);

    if (listenersList == null) {
      listenersList = new LinkedList<T>();
      listeners.put(eventClass, listenersList);
    }

    listenersList.add(listener);

  }

  public void processEvents() {

    while (!events.isEmpty()) {
      Event e = events.poll();
      processEvent(e);
    }

  }

  private void processEvent(Event e) {

    if (e instanceof GameEvent) {

      GameEvent gameEvent = (GameEvent) e;

      LinkedList<GameListener> gameListeners = gameEventListeners.get(e.getClass());

      if (gameListeners == null)
        return;

      for (GameListener listener : gameListeners) {
        listener.onEvent(gameEvent);
      }

    } else if (e instanceof UIEvent) {

      UIEvent uiEvent = (UIEvent) e;

      LinkedList<UIListener> uiListeners = uiEventListeners.get(e.getClass());

      if (uiListeners == null)
        return;

      for (UIListener listener : uiListeners) {
        listener.onEvent(uiEvent);
      }

    }

  }

  public synchronized void addEvent(Event e) {
    events.add(e);
  }

}
