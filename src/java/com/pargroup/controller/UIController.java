package com.pargroup.controller;

import com.pargroup.view.GameView;
import javafx.application.Application;

/**
 * @author Rawad Aboudlal
 *
 */
public class UIController {

  private GameController gameController;

  public void showGame(GameController gameController) {

    this.gameController = gameController;

    GameView.show(gameController.getBoard());

  }

}
