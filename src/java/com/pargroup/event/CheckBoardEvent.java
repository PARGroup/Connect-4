package com.pargroup.event;

import com.pargroup.model.Chip;

/**
 * @author Rawad Aboudlal
 *
 */
public class CheckBoardEvent extends RequestEvent {

  private final Chip lastChipPlaced;

  /**
   * @param lastChipPlaced
   */
  public CheckBoardEvent(Chip lastChipPlaced) {
    super();
    this.lastChipPlaced = lastChipPlaced;
  }

  /**
   * @return the lastChipPlaced
   */
  public Chip getLastChipPlaced() {
    return lastChipPlaced;
  }

}
