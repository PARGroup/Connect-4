package com.pargroup.controller;

import com.pargroup.event.ChipPlacedEvent;
import com.pargroup.event.PlaceChipRequestEvent;
import com.pargroup.event.StopGameEvent;
import com.pargroup.event.StopRequestEvent;
import com.pargroup.event.ResolutionEvent;
import com.pargroup.event.listener.ResolutionListener;
import com.pargroup.model.BoardConfig;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import com.pargroup.view.BoardView;
import com.pargroup.view.GameView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public class UIController implements ResolutionListener, EventHandler<ActionEvent> {

  private GameController gameController;
  private GameView gameView;

  private BoardView boardView;

  private BoardConfig boardConfig;

  /**
   * @param gameController
   * @param gameView
   */
  public UIController(GameController gameController, GameView gameView) {
    super();

    this.gameController = gameController;
    this.gameView = gameView;

  }

  public void initialize() {

    gameController.getEventManager().addResolutionListener(ChipPlacedEvent.class, this);
    gameController.getEventManager().addResolutionListener(StopGameEvent.class, this);

    TextureLoader.loadTextures();

  }

  private void terminate() {
    gameController.terminate();
    gameView.terminate();
  }

  /**
   * @see com.pargroup.event.listener.Listener#onEvent(com.pargroup.event.Event)
   */
  @Override
  public void onEvent(ResolutionEvent e) {

    if (e instanceof ChipPlacedEvent) {

      ChipPlacedEvent chipPlacedEvent = (ChipPlacedEvent) e;

      Chip chip = chipPlacedEvent.getChip();

      int column = chipPlacedEvent.getColumn();
      int row = chipPlacedEvent.getRow();


      final int x = column * (boardConfig.getChipRadius() * 2 + boardConfig.getHgap())
          + (boardConfig.getHgap() / 2);

      // Note that this is negative so it will start slightly above the board.
      final int y = -boardConfig.getVgap() / 2;
      final int endY =
          row * (boardConfig.getChipRadius() * 2 + boardConfig.getVgap()) + boardConfig.getVgap();

      gameController.setPaused(true);

      Platform.runLater(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
          boardView.chipPlaced(UIController.this, chip, x, y, endY);
        }
      });

    } else if (e instanceof StopGameEvent) {

      terminate();

    }

  }

  public void addBoardView(BoardView boardView) {

    this.boardView = boardView;
    this.boardConfig = boardView.getBoardConfig();

    boardView.getClickPane().addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          /**
           * @see javafx.event.EventHandler#handle(javafx.event.Event)
           */
          @Override
          public void handle(MouseEvent event) {

            int x = (int) event.getX();

            int column = x / ((boardConfig.getChipRadius() * 2) + boardConfig.getHgap());

            gameController.getEventManager().addEvent(new PlaceChipRequestEvent(column));

          }
        });

  }

  public void addStage(Stage stage) {

    stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(WindowEvent event) {
        gameController.getEventManager().addEvent(new StopRequestEvent());
      }
    });

  }

  /**
   * @see javafx.event.EventHandler#handle(javafx.event.Event)
   */
  @Override
  public void handle(ActionEvent event) {

    gameController.setPaused(false);

  }

  /**
   * @return the gameController
   */
  public GameController getGameController() {
    return gameController;
  }

}
