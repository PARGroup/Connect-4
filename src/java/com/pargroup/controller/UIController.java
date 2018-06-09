package com.pargroup.controller;

import com.pargroup.event.ChipPlaceEvent;
import com.pargroup.event.UIEvent;
import com.pargroup.event.listener.UIListener;
import com.pargroup.model.BoardConfig;
import com.pargroup.resources.TextureLoader;
import com.pargroup.view.BoardView;
import com.pargroup.view.GameView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * @author Rawad Aboudlal
 *
 */
public class UIController implements UIListener {

  private GameController gameController;

  private BoardView boardView;

  private BoardConfig boardConfig;

  /**
   * @param gameController
   */
  public UIController(GameController gameController) {
    super();

    this.gameController = gameController;

  }

  public void initialize() {

    gameController.addListener(ChipPlaceEvent.class, this);

    TextureLoader.loadTextures();

  }

  /**
   * @see com.pargroup.event.listener.Listener#onEvent(com.pargroup.event.Event)
   */
  @Override
  public void onEvent(UIEvent e) {

    if (e instanceof ChipPlaceEvent) {
      ChipPlaceEvent chipPlaceEvent = (ChipPlaceEvent) e;

      Circle chipView = new Circle(viewConfig.getChipRadius(), chip.getColor());

      final int x = column * (viewConfig.getChipRadius() * 2 + viewConfig.getHgap())
          + viewConfig.getChipRadius() + (viewConfig.getHgap() / 2);
      // vgap * 2 causes a little bounce effect for the top-most chip only.
      final int y = viewConfig.getVgap() * 2 + viewConfig.getChipRadius();
      final int endY = row * (viewConfig.getChipRadius() * 2 + viewConfig.getVgap())
          + viewConfig.getChipRadius() + viewConfig.getVgap();

      GameController.setPaused(true);

      Platform.runLater(new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        @Override
        public void run() {
          GameView.placeChip(chipView, x, y, endY);
        }
      });

    }

  }

  public void addBoardView(BoardView boardView) {

    this.boardView = boardView;
    this.boardConfig = boardView.getBoardConfig();

    boardView.getChipsPane().addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          /**
           * @see javafx.event.EventHandler#handle(javafx.event.Event)
           */
          @Override
          public void handle(MouseEvent event) {

            int x = (int) event.getX();

            int column = (x - (boardConfig.getHorizontalGap() / 2))
                / ((boardConfig.getChipWidth()) + boardConfig.getHorizontalGap());

            gameController.addEvent(new ChipPlaceEvent(column));

          }
        });

  }

  /**
   * @return the gameController
   */
  public GameController getGameController() {
    return gameController;
  }

}
