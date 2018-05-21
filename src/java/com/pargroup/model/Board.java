package com.pargroup.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Rawad Aboudlal
 *
 */
public class Board {

  private final BoardConfig boardConfig;

  private LinkedList<Chip>[] chips;

  /**
   * 
   */
  @SuppressWarnings("unchecked")
  public Board(BoardConfig boardConfig) {
    super();

    this.boardConfig = boardConfig;

    chips = new LinkedList[boardConfig.getColumns()];

    for (int i = 0; i < boardConfig.getColumns(); i++) {

      chips[i] = new LinkedList<Chip>();
    }

  }

  public void clear() {
    for (LinkedList<Chip> col : chips) {
      col.clear();
    }
  }

  public Iterator<Chip> iterator(int column) {
    return chips[column].iterator();
  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;

  }

}
