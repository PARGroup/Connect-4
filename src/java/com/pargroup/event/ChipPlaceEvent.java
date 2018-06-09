package com.pargroup.event;

/**
 * @author Rawad Aboudlal
 *
 */
public class ChipPlaceEvent extends UIEvent {

  private final int column;

  /**
   * @param column
   */
  public ChipPlaceEvent(int column) {
    super();

    this.column = column;

  }

  /**
   * @return the column
   */
  public int getColumn() {
    return column;
  }

}
