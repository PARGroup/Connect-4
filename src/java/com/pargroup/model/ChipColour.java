package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public enum ChipColour {

  RED("red_chip"), BLUE("blue_chip"), YELLOW("yellow_chip");

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
