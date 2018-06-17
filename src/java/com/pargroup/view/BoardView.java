package com.pargroup.view;

import com.pargroup.controller.UIController;
import com.pargroup.model.Board;
import com.pargroup.model.BoardConfig;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import com.pargroup.view.animation.ChipAnimationFactory;
import com.pargroup.view.theme.Theme;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Rawad Aboudlal
 *
 */
public class BoardView extends StackPane {

  private Board board;
  private Theme theme;
  private BoardConfig boardConfig;

  private ImageView backgroundTexture;
  private Pane chipsPane;
  private ImageView boardTexture;
  private Pane clickPane;

  /**
   * @param board
   * @param theme
   */
  public BoardView(Board board, Theme theme) {
    super();

    this.board = board;
    this.theme = theme;
    this.boardConfig = theme.getBoardConfig();

    Image boardImage = TextureLoader.getTexture(TextureLoader.BOARD);

    backgroundTexture = new ImageView();
    chipsPane = new Pane();
    boardTexture = new ImageView(boardImage);
    clickPane = new Pane();

    chipsPane.maxWidthProperty().bind(boardImage.widthProperty());
    chipsPane.maxHeightProperty().bind(boardImage.heightProperty());

    clickPane.maxWidthProperty().bind(boardImage.widthProperty());
    clickPane.maxHeightProperty().bind(boardImage.heightProperty());

    getChildren().add(backgroundTexture);
    getChildren().add(chipsPane);
    getChildren().add(boardTexture);
    getChildren().add(clickPane);

  }

  public void chipPlaced(UIController uiController, Chip chip, int x, int startY, int endY) {

    chip.setColour(theme.getChipColours()[chip.getOwner().getTurnIndex()]);

    ChipView chipView = new ChipView(chip);

    Animation chipPlacementAnimation = ChipAnimationFactory
        .createAnimation(theme.getChipPlacementAnimation(), chipView, x, startY, endY);

    chipPlacementAnimation.setOnFinished(new EventHandler<ActionEvent>() {

      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(ActionEvent event) {
        uiController.onChipPlaced(chipView, x, endY);
      }

    });

    chipsPane.getChildren().add(chipView);
    chipPlacementAnimation.playFromStart();

  }

  /**
   * @return the clickPane
   */
  public Pane getClickPane() {
    return clickPane;
  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;
  }

}
