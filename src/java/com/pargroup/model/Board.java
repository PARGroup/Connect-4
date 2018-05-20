package com.pargroup.model;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Rawad Aboudlal
 *
 */
public class Board {

  private final int width = 7;
  private final int height = 6;

  private LinkedList<Chip>[] chips;

  /**
   * 
   */
  @SuppressWarnings("unchecked")
  public Board() {
    super();

    chips = new LinkedList[width];

    for (int i = 0; i < width; i++) {
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
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }

}
