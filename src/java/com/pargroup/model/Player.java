package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Player {

  private final ChipColour chipColour;

  /**
   * @param chipColour
   */
  public Player(ChipColour chipColour) {
    super();

    this.chipColour = chipColour;

  }

  /**
   * @return the chipColour
   */
  public ChipColour getChipColour() {
    return chipColour;
  }

}
