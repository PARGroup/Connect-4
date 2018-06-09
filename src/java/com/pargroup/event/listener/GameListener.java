package com.pargroup.event.listener;

import com.pargroup.event.GameEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public interface GameListener extends Listener {

  public void onEvent(GameEvent e);

}
