package com.pargroup.view;

import com.pargroup.model.Board;
import com.pargroup.model.BoardConfig;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class BoardView extends StackPane {

  private Board board;
  private BoardConfig boardConfig;

  private ImageView backgroundTexture;
  private Pane chipsPane;
  private ImageView boardTexture;
  private Pane clickPane;

  /**
   * @param board
   */
  public BoardView(Board board) {
    super();

    this.board = board;
    this.boardConfig = board.getBoardConfig();

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

  public void chipPlaced(EventHandler<ActionEvent> onAnimationFinishedHandler, Chip chip, int x,
      int startY, int endY) {

    ChipView chipView = new ChipView(chip);

    TranslateTransition translation = new TranslateTransition(Duration.millis(100), chipView);
    translation.setFromX(x);
    translation.setFromY(startY);
    translation.setToX(x);
    translation.setToY(endY);

    translation.setOnFinished(onAnimationFinishedHandler);

    chipsPane.getChildren().add(chipView);
    translation.playFromStart();

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
