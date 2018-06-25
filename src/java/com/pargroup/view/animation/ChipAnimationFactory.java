package com.pargroup.view.animation;

import java.util.HashMap;
import com.pargroup.view.ChipView;
import javafx.animation.Animation;

/**
 * @author Rawad Aboudlal
 *
 */
public abstract class ChipAnimationFactory {

  private static final HashMap<String, ChipAnimationFactory> FACTORIES =
      new HashMap<String, ChipAnimationFactory>();

  private final String key;

  private ChipView chipView;

  private int x;

  private int startY;
  private int endY;

  private Sprite sprite;

  /**
   * @param key
   */
  public ChipAnimationFactory(String key) {
    super();

    this.key = key;

    if (getAnimationFactory(key) == null) {
      FACTORIES.put(key, this);
    } else {
      System.err
          .println("WARNING: This ChipAnimationFactory (" + key + ") has already been loaded.");
    }

  }

  public static Animation createAnimation(String key, ChipView chipView, int x, int startY,
      int endY, Sprite sprite) {

    ChipAnimationFactory animationFactory = FACTORIES.get(key);

    animationFactory.setChipView(chipView);
    animationFactory.setX(x);
    animationFactory.setStartY(startY);
    animationFactory.setEndY(endY);
    animationFactory.setSprite(sprite);

    return animationFactory.createAnimation();

  }

  protected abstract Animation createAnimation();

  /**
   * 
   * @param key
   * @return
   */
  public static ChipAnimationFactory getAnimationFactory(String key) {
    return FACTORIES.get(key);
  }

  /**
   * @return the key
   */
  public final String getKey() {
    return key;
  }

  /**
   * @return the chipView
   */
  public ChipView getChipView() {
    return chipView;
  }

  /**
   * @param chipView the chipView to set
   */
  public void setChipView(ChipView chipView) {
    this.chipView = chipView;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @param x the x to set
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * @return the startY
   */
  public int getStartY() {
    return startY;
  }

  /**
   * @param startY the startY to set
   */
  public void setStartY(int startY) {
    this.startY = startY;
  }

  /**
   * @return the endY
   */
  public int getEndY() {
    return endY;
  }

  /**
   * @param endY the endY to set
   */
  public void setEndY(int endY) {
    this.endY = endY;
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

}
