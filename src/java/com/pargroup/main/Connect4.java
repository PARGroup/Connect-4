package com.pargroup.main;

import com.pargroup.controller.GameController;
import com.pargroup.controller.UIController;

/**
 * @author Rawad Aboudlal
 *
 */
public class Connect4 {

  private static GameController gameController;
  private static UIController uiController;

  /**
   * @param args
   */
  public static void main(String[] args) {

    gameController = new GameController();
    uiController = new UIController();

    uiController.showGame(gameController);

  }

}
