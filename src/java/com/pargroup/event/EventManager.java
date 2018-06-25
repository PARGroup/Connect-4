package com.pargroup.event;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import com.pargroup.event.listener.Listener;
import com.pargroup.event.listener.RequestListener;
import com.pargroup.event.listener.ResolutionListener;

/**
 * @author Rawad Aboudlal
 *
 */
public class EventManager {


  private HashMap<Class<?>, LinkedList<RequestListener>> requestEventListeners =
      new HashMap<Class<?>, LinkedList<RequestListener>>();
  private HashMap<Class<?>, LinkedList<ResolutionListener>> resolutionEventListeners =
      new HashMap<Class<?>, LinkedList<ResolutionListener>>();

  private Queue<Event> events = new LinkedList<Event>();

  private Set<Class<?>> blockedEvents = new LinkedHashSet<Class<?>>();

  public void addRequestListener(Class<? extends RequestEvent> eventClass,
      RequestListener listener) {
    addGenericListener(requestEventListeners, eventClass, listener);
  }

  public void addResolutionListener(Class<? extends ResolutionEvent> eventClass,
      ResolutionListener listener) {
    addGenericListener(resolutionEventListeners, eventClass, listener);
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

    if (e instanceof RequestEvent) {

      RequestEvent gameEvent = (RequestEvent) e;

      LinkedList<RequestListener> gameListeners = requestEventListeners.get(e.getClass());

      if (gameListeners == null)
        return;

      for (RequestListener listener : gameListeners) {
        listener.onEvent(gameEvent);
      }

    } else if (e instanceof ResolutionEvent) {

      ResolutionEvent uiEvent = (ResolutionEvent) e;

      LinkedList<ResolutionListener> uiListeners = resolutionEventListeners.get(e.getClass());

      if (uiListeners == null)
        return;

      for (ResolutionListener listener : uiListeners) {
        listener.onEvent(uiEvent);
      }

    }

  }

  public synchronized void addEvent(Event e) {

    for (Class<?> blockedEventClass : blockedEvents) {

      if (e.getClass().equals(blockedEventClass)) {
        return;
      }

    }

    events.add(e);

  }

  public void blockEvent(Class<?> eventToBlock) {
    blockedEvents.add(eventToBlock);
  }

  public void unblockEvent(Class<?> eventToUnblock) {
    blockedEvents.remove(eventToUnblock);
  }

}
