package com.pargroup.view;

import com.pargroup.model.Board;
import com.pargroup.model.BoardConfig;
import com.pargroup.model.Chip;
import com.pargroup.model.ChipColour;
import com.pargroup.resources.ConfigsLoader;
import com.pargroup.resources.TextureLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView extends Application {

  private static final String WINDOW_TITLE = "Connect 4";

  private static Stage stage;
  private static BorderPane root;
  private static BoardView boardView;

  private static Board board;

  public static void show(Board board) {

    GameView.board = board;

    TextureLoader.loadTextures();

    new Thread(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        Application.launch(GameView.class);
      }
    }, "GUI Thread").start();

  }

  /**
   * @see javafx.application.Application#start(javafx.stage.Stage)
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;

    root = new BorderPane();

    Scene scene = new Scene(root);

    constructView();

    // root.setPrefSize(640, 480);

    stage.setScene(scene);
    stage.sizeToScene();
    stage.centerOnScreen();
    stage.setTitle(WINDOW_TITLE);
    stage.show();
    stage.setMinWidth(stage.getWidth());
    stage.setMinHeight(stage.getHeight());

  }

  private void constructView() {

    boardView = new BoardView(board);

    root.setCenter(boardView);

    Chip mockChip = new Chip();
    mockChip.setColour(ChipColour.RED);
    boardView.chipPlaced(mockChip, 6, 7);

  }

}
