package com.pargroup.view;

import com.pargroup.controller.UIController;
import com.pargroup.event.DrawEvent;
import com.pargroup.event.GameOverEvent;
import com.pargroup.event.PlayerWinEvent;
import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.model.Board;
import com.pargroup.resources.ThemeManager;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView implements ThemeChangeListener {

  private static final String WINDOW_TITLE = "Connect 4";

  private UIController uiController;

  private Stage stage;
  private BorderPane root;
  private BoardView boardView;

  private Stage dialogStage;

  private Board board;

  public void show(Stage primaryStage, UIController uiController) {

    stage = primaryStage;

    this.uiController = uiController;

    board = uiController.getGameController().getBoard();

    uiController.addStage(stage);

    root = new BorderPane();

    Scene scene = new Scene(root);

    constructView();

    uiController.addBoardView(boardView);
    uiController.addPlacementIndicatorView(boardView.getPlacementIndicatorView());

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

  public void showGameOverDialog(GameOverEvent gameOverEvent) {

    Platform.runLater(new Runnable() {

      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {

        dialogStage = new Stage();
        dialogStage.setResizable(false);

        VBox root = new VBox();

        Scene scene = new Scene(root);

        Text message = new Text();
        message.setTextAlignment(TextAlignment.CENTER);
        VBox.setVgrow(message, Priority.ALWAYS);

        if (gameOverEvent instanceof DrawEvent) {
          message.setText("It's a draw!");
        } else if (gameOverEvent instanceof PlayerWinEvent) {

          PlayerWinEvent playerWinEvent = (PlayerWinEvent) gameOverEvent;
          message.setText(playerWinEvent.getWinner().getName() + " Wins!");


        }

        Button playAgainButton = new Button("Play Again!");
        Button quitButton = new Button("Quit");

        uiController.addPlayAgainButton(playAgainButton);
        uiController.addQuitButton(quitButton);

        HBox buttonHolder = new HBox();

        buttonHolder.getChildren().add(playAgainButton);
        buttonHolder.getChildren().add(quitButton);

        root.getChildren().add(message);
        root.getChildren().add(buttonHolder);

        dialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          /**
           * @see javafx.event.EventHandler#handle(javafx.event.Event)
           */
          @Override
          public void handle(WindowEvent event) {
            event.consume();
          }
        });
        dialogStage.setScene(scene);
        dialogStage.sizeToScene();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();

      }
    });

  }

  public void hideGameOverDialog() {

    if (dialogStage != null) {
      dialogStage.close();
      dialogStage = null;
    }

  }

  public void terminate() {
    Platform.runLater(new Runnable() {

      /**
       * 
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        hideGameOverDialog();
        stage.close();
      }

    });
  }

}
