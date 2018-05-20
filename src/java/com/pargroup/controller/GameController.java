package com.pargroup.controller;

import com.pargroup.model.Board;

/**
 * @author Rawad Aboudlal
 *
 */
public class GameController {

  private Board board;

  /**
   * 
   */
  public GameController() {
    super();

    board = new Board();

  }

  /**
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

}
