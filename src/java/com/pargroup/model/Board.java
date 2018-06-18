package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Board {

  private final BoardConfig boardConfig;

  private Chip[][] chips;

  /**
   * @param boardConfig
   */
  public Board(BoardConfig boardConfig) {
    super();

    this.boardConfig = boardConfig;

    chips = new Chip[boardConfig.getRows()][boardConfig.getColumns()];

  }

  public void clear() {

    for (int i = 0; i < chips.length; i++) {

      Chip[] row = chips[i];

      for (int j = 0; j < row.length; j++) {
        row[j] = null;
      }

    }

  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;

  }

  /**
   * @return the chips
   */
  public Chip[][] getChips() {
    return chips;
  }

}
