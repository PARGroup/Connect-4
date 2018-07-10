package com.pargroup.controller;

import com.pargroup.event.ChipPlacedEvent;
import com.pargroup.event.PlaceChipRequestEvent;
import com.pargroup.event.ResolutionEvent;
import com.pargroup.event.StopGameEvent;
import com.pargroup.event.StopRequestEvent;
import com.pargroup.event.listener.ResolutionListener;
import com.pargroup.model.Chip;
import com.pargroup.resources.ThemeManager;
import com.pargroup.view.BoardConfig;
import com.pargroup.view.BoardView;
import com.pargroup.view.ChipView;
import com.pargroup.view.GameView;
import com.pargroup.view.PlacementIndicatorView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Rawad Aboudlal
 *
 */
public class UIController implements ResolutionListener {

  private GameController gameController;
  private GameView gameView;

  private BoardView boardView;

  private BoardConfig boardConfig;

  private PlacementIndicatorView placementIndicatorView;

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

      gameController.getEventManager().blockEvent(PlaceChipRequestEvent.class);

      ChipPlacedEvent chipPlacedEvent = (ChipPlacedEvent) e;

      Chip chip = chipPlacedEvent.getChip();

      int column = chipPlacedEvent.getColumn();
      int row = chipPlacedEvent.getRow();


      final int x = getXFromColumn(column);

      // Note that this is negative so it will start slightly above the board.
      final int y = -boardConfig.getVgap() / 2;
      final int endY =
          row * (boardConfig.getChipRadius() * 2 + boardConfig.getVgap()) + boardConfig.getVgap();

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

    boardView.getBoardTextureView().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
          /**
           * @see javafx.event.EventHandler#handle(javafx.event.Event)
           */
          @Override
          public void handle(MouseEvent event) {

            if (event.getButton().equals(MouseButton.PRIMARY)) {

              int x = (int) event.getX();
              int column = getColumnFromX(x);

              gameController.getEventManager().addEvent(new PlaceChipRequestEvent(column));

            }

          }
        });

    boardView.getBoardTextureView().addEventHandler(MouseEvent.MOUSE_MOVED,
        new EventHandler<MouseEvent>() {
          /**
           * @see javafx.event.EventHandler#handle(javafx.event.Event)
           */
          @Override
          public void handle(MouseEvent event) {

            int x = (int) event.getX();
            int column = getColumnFromX(x);

            updatePlacementIndicatorViewPosition(column);

          }
        });

  }

  /**
   * Converts the given <code>x</code> coordinate to its corresponding column index on the board,
   * depending on the <code>boardConfig</code>.
   * 
   * @param x
   * @return
   */
  private int getColumnFromX(int x) {
    return x / ((boardConfig.getChipRadius() * 2) + boardConfig.getHgap());
  }

  /**
   * Converts the given <code>column</code> index on the board into an actual x-coordinate that can
   * be used to position nodes on the board, depending on the <code>boardConfig</code>.
   * 
   * @param column
   * @return
   */
  private int getXFromColumn(int column) {
    return column * (boardConfig.getChipRadius() * 2 + boardConfig.getHgap())
        + (boardConfig.getHgap() / 2);
  }

  private void updatePlacementIndicatorViewPosition(int column) {

    int properX = getXFromColumn(column);

    placementIndicatorView.setTranslateX(properX);

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

    stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(KeyEvent event) {

        if (event.getCode().equals(KeyCode.NUMPAD1)) {
          ThemeManager.setTheme(ThemeManager.DEFAULT_THEME);
        } else if (event.getCode().equals(KeyCode.NUMPAD2)) {
          ThemeManager.setTheme(ThemeManager.PENCIL_THEME);
        }

      }
    });

  }

  /**
   * @param placementIndicatorView
   */
  public void addPlacementIndicatorView(PlacementIndicatorView placementIndicatorView) {

    this.placementIndicatorView = placementIndicatorView;

    placementIndicatorView.setCurrentPlayer(gameController.getCurrentPlayer());
    updatePlacementIndicatorViewPosition(0);

  }

  public void onChipPlaced(ChipView chipView, int x, int y) {

    chipView.setTranslateX(x);
    chipView.setTranslateY(y);

    gameController.getEventManager().unblockEvent(PlaceChipRequestEvent.class);

    placementIndicatorView.setCurrentPlayer(gameController.getCurrentPlayer());

  }

  /**
   * @return the gameController
   */
  public GameController getGameController() {
    return gameController;
  }

}
