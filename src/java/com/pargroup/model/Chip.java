package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Chip {

  private Player owner;
  private ChipColour colour;

  /**
   * @return the owner
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * @param owner the owner to set
   */
  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * @return the colour
   */
  public ChipColour getColour() {
    return colour;
  }

  /**
   * @param colour the colour to set
   */
  public void setColour(ChipColour colour) {
    this.colour = colour;
  }

}
