package com.pargroup.view;

import com.pargroup.model.Board;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * @author Rawad Aboudlal
 *
 */
public class BoardView extends StackPane {

  private Board board;

  private ImageView boardTexture;
  private GridPane chipsPane;

  /**
   * @param board
   */
  public BoardView(Board board) {
    super();

    this.board = board;

    boardTexture = new ImageView(TextureLoader.getTexture("board"));
    chipsPane = new GridPane();

    chipsPane.setHgap(8);
    chipsPane.setVgap(8);
    chipsPane.setPadding(new Insets(8, 0, 0, 8));

    getChildren().add(boardTexture);
    getChildren().add(chipsPane);

  }

  public void chipPlaced(Chip chip, int column, int row) {

    ChipView chipView = new ChipView(chip);

    chipsPane.add(chipView, column, row);

  }

}
