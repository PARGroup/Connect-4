package com.pargroup.controller;

import com.pargroup.view.GameView;
import javafx.application.Application;

/**
 * @author Rawad Aboudlal
 *
 */
public class UIController {

  private GameView gameView;
  private GameController gameController;

  /**
   * 
   */
  public UIController() {
    super();

    this.gameView = new GameView();

  }

  public void showGame(GameController gameController) {

    this.gameController = gameController;

    gameView.show();

  }

}
