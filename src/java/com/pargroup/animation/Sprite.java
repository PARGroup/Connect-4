package com.pargroup.animation;

import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class Sprite {

  private Duration duration;

  private int numberOfFrames;

  private int columns;

  private int cellWidth;
  private int cellHeight;

  /**
   * @return the duration
   */
  public Duration getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  /**
   * @return the numberOfFrames
   */
  public int getNumberOfFrames() {
    return numberOfFrames;
  }

  /**
   * @param numberOfFrames the numberOfFrames to set
   */
  public void setNumberOfFrames(int numberOfFrames) {
    this.numberOfFrames = numberOfFrames;
  }

  /**
   * @return the columns
   */
  public int getColumns() {
    return columns;
  }

  /**
   * @param columns the columns to set
   */
  public void setColumns(int columns) {
    this.columns = columns;
  }

  /**
   * @return the cellWidth
   */
  public int getCellWidth() {
    return cellWidth;
  }

  /**
   * @param cellWidth the cellWidth to set
   */
  public void setCellWidth(int cellWidth) {
    this.cellWidth = cellWidth;
  }

  /**
   * @return the cellHeight
   */
  public int getCellHeight() {
    return cellHeight;
  }

  /**
   * @param cellHeight the cellHeight to set
   */
  public void setCellHeight(int cellHeight) {
    this.cellHeight = cellHeight;
  }

}
