package com.pargroup.view;

import com.pargroup.controller.UIController;
import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.model.Board;
import com.pargroup.resources.ThemeManager;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView implements ThemeChangeListener {

  private static final String WINDOW_TITLE = "Connect 4";

  private Stage stage;
  private BorderPane root;
  private BoardView boardView;

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

    ThemeManager.initializeThemes();

    ThemeManager.setTheme(ThemeManager.PENCIL_THEME);

    boardView = new BoardView(board);

    // It is very important that the GameView is added as a ThemeChangeListener after the BoardView
    // (which is done upon initialization) so that the new theme's textures can be used to properly
    // resize the Stage.
    ThemeManager.addThemeChangeListener(this);

    root.setCenter(boardView);

  }

  /**
   * @see com.pargroup.event.listener.ThemeChangeListener#onThemeChange()
   */
  @Override
  public void onThemeChange() {

    Platform.runLater(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {

        stage.sizeToScene();

        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        stage.centerOnScreen();

      }
    });

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
