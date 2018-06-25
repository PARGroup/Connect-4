package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public enum ChipColour {

  RED("red_chip"), BLUE("blue_chip"), YELLOW("yellow_chip"), ANIMATED_RED(
      "red_drawn_chip"), RED_SPRITE("red_chip_sprite"), BLUE_SPRITE("blue_chip_sprite");

  private final String chipName;

  /**
   * @param chipName
   */
  private ChipColour(String chipName) {
    this.chipName = chipName;
  }

  /**
   * @return the chipName
   */
  public String getChipName() {
    return chipName;
  }

}
