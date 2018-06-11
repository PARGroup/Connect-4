package com.pargroup.view.animation;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class DropAnimationFactory extends AnimationFactory {

  public static final String KEY = "DropAnimationFactory";

  /**
   * 
   */
  public DropAnimationFactory() {
    super(KEY);
  }

  /**
   * @see com.pargroup.view.animation.AnimationFactory#createAnimation()
   */
  @Override
  protected Animation createAnimation() {

    TranslateTransition chipPlacedAnimation =
        new TranslateTransition(Duration.millis(100), getNode());

    chipPlacedAnimation.setFromX(getX());
    chipPlacedAnimation.setFromY(getStartY());
    chipPlacedAnimation.setToX(getX());
    chipPlacedAnimation.setToY(getEndY());

    return chipPlacedAnimation;

  }

}
