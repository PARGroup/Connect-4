package com.pargroup.animation;

import com.pargroup.view.ChipView;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class DropAnimationFactory extends ChipAnimationFactory {

  /**
   * @see com.pargroup.animation.ChipAnimationFactory#createAnimation()
   */
  @Override
  public Animation createAnimation(ChipView chipView, int x, int startY, int endY) {

    TranslateTransition chipPlacedAnimation =
        new TranslateTransition(Duration.millis(100), chipView);

    chipPlacedAnimation.setFromX(x);
    chipPlacedAnimation.setFromY(startY);
    chipPlacedAnimation.setToX(x);
    chipPlacedAnimation.setToY(endY);

    return chipPlacedAnimation;

  }

  /**
   * @see com.pargroup.animation.ChipAnimationFactory#resetViewport(com.pargroup.view.ChipView)
   */
  @Override
  public void resetViewport(ChipView chipView) {

    chipView.setViewport(new Rectangle2D(0, 0, chipView.getFitWidth(), chipView.getFitHeight()));

  }

}
