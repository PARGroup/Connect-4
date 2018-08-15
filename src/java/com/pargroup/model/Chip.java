package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Chip {

  private Player owner;
  private String colour;

  private int column;
  private int row;

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

  /**
   * @return the column
   */
  public int getColumn() {
    return column;
  }

  /**
   * @param column the column to set
   */
  public void setColumn(int column) {
    this.column = column;
  }

  /**
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /**
   * @param row the row to set
   */
  public void setRow(int row) {
    this.row = row;
  }

}
