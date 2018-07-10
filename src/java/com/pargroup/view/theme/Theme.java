package com.pargroup.view.theme;

import java.io.File;
import java.util.List;
import com.pargroup.animation.ChipAnimationFactory;
import com.pargroup.animation.Sprite;
import com.pargroup.view.BoardConfig;

/**
 * @author Rawad Aboudlal
 *
 */
public class Theme {

  private File folder;

  private List<String> chipColours;

  private ChipAnimationFactory chipPlacementAnimation;

  private String backgroundTexture;
  private String boardTexture;

  private BoardConfig boardConfig;

  private Sprite sprite;

  private List<String> indicatorTextures;

  /**
   * @return the folder
   */
  public File getFolder() {
    return folder;
  }

  /**
   * @param folder the folder to set
   */
  public void setFolder(File folder) {
    this.folder = folder;
  }

  /**
   * @return the chipColours
   */
  public List<String> getChipColours() {
    return chipColours;
  }

  /**
   * @param chipColours the chipColours to set
   */
  public void setChipColours(List<String> chipColours) {
    this.chipColours = chipColours;
  }

  /**
   * @return the chipPlacementAnimation
   */
  public ChipAnimationFactory getChipPlacementAnimation() {
    return chipPlacementAnimation;
  }

  /**
   * @param chipPlacementAnimation the chipPlacementAnimation to set
   */
  public void setChipPlacementAnimation(ChipAnimationFactory chipPlacementAnimation) {
    this.chipPlacementAnimation = chipPlacementAnimation;
  }

  /**
   * @return the backgroundTexture
   */
  public String getBackgroundTexture() {
    return backgroundTexture;
  }

  /**
   * @param backgroundTexture the backgroundTexture to set
   */
  public void setBackgroundTexture(String backgroundTexture) {
    this.backgroundTexture = backgroundTexture;
  }

  /**
   * @return the boardTexture
   */
  public String getBoardTexture() {
    return boardTexture;
  }

  /**
   * @param boardTexture the boardTexture to set
   */
  public void setBoardTexture(String boardTexture) {
    this.boardTexture = boardTexture;
  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;
  }

  /**
   * @param boardConfig the boardConfig to set
   */
  public void setBoardConfig(BoardConfig boardConfig) {
    this.boardConfig = boardConfig;
  }

  /**
   * @return the sprite
   */
  public Sprite getSprite() {
    return sprite;
  }

  /**
   * @param sprite the sprite to set
   */
  public void setSprite(Sprite sprite) {
    this.sprite = sprite;
  }

  /**
   * @return the indicatorTextures
   */
  public List<String> getIndicatorTextures() {
    return indicatorTextures;
  }

  /**
   * @param indicatorTextures the indicatorTextures to set
   */
  public void setIndicatorTextures(List<String> indicatorTextures) {
    this.indicatorTextures = indicatorTextures;
  }

}
