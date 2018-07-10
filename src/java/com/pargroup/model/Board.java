package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Board {

  public static final int ROWS = 6;
  public static final int COLUMNS = 7;

  private Chip[][] chips;

  /**
   * @param rows
   * @param columns
   */
  public Board(int rows, int columns) {
    super();

    chips = new Chip[rows][columns];

  }

  /**
   * 
   */
  public Board() {
    this(ROWS, COLUMNS);
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
   * @return the chips
   */
  public Chip[][] getChips() {
    return chips;
  }

}
