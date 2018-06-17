package com.pargroup.view.animation;

import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class Sprite {

  private final Duration duration;

  private final int numberOfFrames;

  private final int columns;

  private final int cellWidth;
  private final int cellHeight;

  /**
   * @param duration
   * @param numberOfFrames
   * @param columns
   * @param cellWidth
   * @param cellHeight
   */
  public Sprite(Duration duration, int numberOfFrames, int columns, int cellWidth, int cellHeight) {
    super();
    this.duration = duration;
    this.numberOfFrames = numberOfFrames;
    this.columns = columns;
    this.cellWidth = cellWidth;
    this.cellHeight = cellHeight;
  }

  /**
   * @return the duration
   */
  public Duration getDuration() {
    return duration;
  }

  /**
   * @return the numberOfFrames
   */
  public int getNumberOfFrames() {
    return numberOfFrames;
  }

  /**
   * @return the columns
   */
  public int getColumns() {
    return columns;
  }

  /**
   * @return the cellWidth
   */
  public int getCellWidth() {
    return cellWidth;
  }

  /**
   * @return the cellHeight
   */
  public int getCellHeight() {
    return cellHeight;
  }

}
