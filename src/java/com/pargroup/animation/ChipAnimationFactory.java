package com.pargroup.animation;

import com.pargroup.view.ChipView;
import javafx.animation.Animation;

/**
 * @author Rawad Aboudlal
 *
 */
public abstract class ChipAnimationFactory {

  // private ChipView chipView;
  // private int x;
  // private int startY;
  // private int endY;

  // public static Animation createAnimation(String key, ChipView chipView, int x, int startY,
  // int endY) {
  //
  // ChipAnimationFactory animationFactory = FACTORIES.get(key);
  //
  // animationFactory.setChipView(chipView);
  // animationFactory.setX(x);
  // animationFactory.setStartY(startY);
  // animationFactory.setEndY(endY);
  //
  // return animationFactory.createAnimation();
  //
  // }

  public abstract Animation createAnimation(ChipView chipView, int x, int startY, int endY);

  public abstract void resetViewport(ChipView chipView);

}
