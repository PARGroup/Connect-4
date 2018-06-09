package com.pargroup.event.listener;

import com.pargroup.event.RequestEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public interface RequestListener extends Listener {

  public void onEvent(RequestEvent e);

}
