package com.pargroup.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameView extends Application {

  private static final String WINDOW_TITLE = "Connect 4";

  private Stage stage;

  public void show() {

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

    this.stage = primaryStage;

    Pane root = new Pane();

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.setTitle(WINDOW_TITLE);
    stage.show();

  }

}
