package com.pargroup.event;

import com.pargroup.model.Chip;

/**
 * @author Rawad Aboudlal
 *
 */
public class ChipPlacedEvent extends UIEvent {

  private final Chip chip;

  private final int column;
  private final int row;

  /**
   * @param chip
   * @param column
   * @param row
   */
  public ChipPlacedEvent(Chip chip, int column, int row) {
    super();

    this.chip = chip;
    this.column = column;
    this.row = row;

  }

  /**
   * @return the chip
   */
  public Chip getChip() {
    return chip;
  }

  /**
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /**
   * @return the row
   */
  public int getRow() {
    return row;
  }

}
