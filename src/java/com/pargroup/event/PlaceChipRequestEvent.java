package com.pargroup.event;

/**
 * @author Rawad Aboudlal
 *
 */
public class PlaceChipRequestEvent extends GameEvent {

  private final int column;

  /**
   * @param column
   */
  public PlaceChipRequestEvent(int column) {
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
