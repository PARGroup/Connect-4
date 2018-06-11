package com.pargroup.view.theme;

import com.pargroup.model.BoardConfig;
import com.pargroup.model.ChipColour;
import javafx.animation.Transition;

/**
 * @author Rawad Aboudlal
 *
 */
public class Theme {

  private final ChipColour[] chipColours;

  private final String chipPlacementAnimation;

  private final String backgroundTexture;
  private final String boardTexture;

  private final BoardConfig boardConfig;

  /**
   * @param chipColours
   * @param chipPlacementAnimation
   * @param backgroundTexture
   * @param boardTexture
   * @param boardConfig
   */
  public Theme(ChipColour[] chipColours, String chipPlacementAnimation, String backgroundTexture,
      String boardTexture, BoardConfig boardConfig) {
    super();

    this.chipColours = chipColours;
    this.chipPlacementAnimation = chipPlacementAnimation;
    this.backgroundTexture = backgroundTexture;
    this.boardTexture = boardTexture;
    this.boardConfig = boardConfig;

  }

  /**
   * @return the chipColours
   */
  public ChipColour[] getChipColours() {
    return chipColours;
  }

  /**
   * @return the chipPlacementAnimation
   */
  public String getChipPlacementAnimation() {
    return chipPlacementAnimation;
  }

  /**
   * @return the backgroundTexture
   */
  public String getBackgroundTexture() {
    return backgroundTexture;
  }

  /**
   * @return the boardTexture
   */
  public String getBoardTexture() {
    return boardTexture;
  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;
  }

}
