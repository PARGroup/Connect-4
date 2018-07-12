package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Chip {

  private Player owner;
  private String colour;

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
  public String getColour() {
    return colour;
  }

  /**
   * @param colour the colour to set
   */
  public void setColour(String colour) {
    this.colour = colour;
  }

}
