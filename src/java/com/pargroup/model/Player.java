package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Player {

  private String colour;

  private String name;

  /**
   * @param colour
   * @param name
   */
  public Player(String colour, String name) {
    super();
    this.colour = colour;
    this.name = name;
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

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

}
