package com.pargroup.view;

import com.pargroup.model.Board;
import com.pargroup.model.BoardConfig;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

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

  /**
   * @param board
   */
  public BoardView(Board board) {
    super();

    this.board = board;
    this.boardConfig = board.getBoardConfig();

    backgroundTexture = new ImageView();
    chipsPane = new Pane();
    boardTexture = new ImageView(TextureLoader.getTexture("board"));

    getChildren().add(backgroundTexture);
    getChildren().add(chipsPane);
    getChildren().add(boardTexture);

  }

  public void chipPlaced(Chip chip, int column, int row) {

    ChipView chipView = new ChipView(chip);

  }

  /**
   * @return the chipsPane
   */
  public Pane getChipsPane() {
    return chipsPane;
  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;
  }

}
