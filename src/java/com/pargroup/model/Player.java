package com.pargroup.model;

/**
 * @author Rawad Aboudlal
 *
 */
public class Player {

  private final int turnIndex;

  /**
   * @param turnIndex
   */
  public Player(int turnIndex) {
    super();

    this.turnIndex = turnIndex;

  }

  /**
   * @return the turnIndex
   */
  public int getTurnIndex() {
    return turnIndex;
  }

}
