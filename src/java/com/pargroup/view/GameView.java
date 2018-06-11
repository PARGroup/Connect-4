package com.pargroup.view;

import com.pargroup.controller.UIController;
import com.pargroup.model.Board;
import com.pargroup.view.theme.Theme;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView {

  private static final String WINDOW_TITLE = "Connect 4";

  private Stage stage;
  private BorderPane root;
  private BoardView boardView;

  private Theme theme;

  private Board board;

  public void show(Stage primaryStage, UIController uiController) {

    stage = primaryStage;

    board = uiController.getGameController().getBoard();

    uiController.addStage(stage);

    root = new BorderPane();

    Scene scene = new Scene(root);

    constructView();

    uiController.addBoardView(boardView);

    stage.setScene(scene);
    stage.sizeToScene();
    stage.centerOnScreen();
    stage.setTitle(WINDOW_TITLE);
    stage.show();
    stage.setMinWidth(stage.getWidth());
    stage.setMinHeight(stage.getHeight());

  }

  private void constructView() {

    boardView = new BoardView(board, theme);

    root.setCenter(boardView);

  }

  public void terminate() {
    Platform.runLater(new Runnable() {

      /**
       * 
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        stage.close();
      }

    });
  }

}
