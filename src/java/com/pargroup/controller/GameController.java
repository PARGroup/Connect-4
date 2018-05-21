package com.pargroup.controller;

import com.pargroup.model.Board;
import com.pargroup.resources.ConfigsLoader;

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

    board = new Board(ConfigsLoader.getBoardConfig());

  }

  /**
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

}
