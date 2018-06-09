package com.pargroup.main;

import com.pargroup.controller.GameController;
import com.pargroup.controller.UIController;
import com.pargroup.view.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class Connect4 extends Application {

  private static GameController gameController;
  private static UIController uiController;
  private static GameView gameView;

  /**
   * @param args
   */
  public static void main(String[] args) {

    gameController = new GameController();
    uiController = new UIController(gameController);
    gameView = new GameView();

    Application.launch(args);

  }

  /**
   * @see javafx.application.Application#start(javafx.stage.Stage)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    gameController.initialize();
    uiController.initialize();

    gameView.show(primaryStage, uiController);

  }

}
