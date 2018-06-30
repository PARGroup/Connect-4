package com.pargroup.animation;

import com.pargroup.resources.ThemeManager;
import com.pargroup.view.ChipView;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class SpriteAnimationFactory extends ChipAnimationFactory {

  /**
   * @see com.pargroup.animation.ChipAnimationFactory#createAnimation()
   */
  @Override
  public Animation createAnimation(ChipView chipView, int x, int startY, int endY) {

    ParallelTransition animation = new ParallelTransition();

    TranslateTransition placementTransition = new TranslateTransition(Duration.millis(0), chipView);

    placementTransition.setFromX(x);
    placementTransition.setToX(x);
    placementTransition.setFromY(startY);
    placementTransition.setToY(endY);

    Sprite sprite = ThemeManager.getCurrentTheme().getSprite();

    if (sprite == null) {
      System.err.println("The sprite for this SpriteAnimationFactory was not set.");
    } else {

      SpriteTransition spriteTransition = new SpriteTransition(sprite, chipView);
      animation.getChildren().add(spriteTransition);

    }

    animation.getChildren().add(placementTransition);

    return animation;

  }

  /**
   * @see com.pargroup.animation.ChipAnimationFactory#resetViewport(com.pargroup.view.ChipView)
   */
  @Override
  public void resetViewport(ChipView chipView) {

    Sprite sprite = ThemeManager.getCurrentTheme().getSprite();

    int x = (sprite.getNumberOfFrames() - 1) % sprite.getColumns() * sprite.getCellWidth();
    int y = (sprite.getNumberOfFrames() - 1) / sprite.getColumns() * sprite.getCellHeight();

    chipView.setViewport(new Rectangle2D(x, y, sprite.getCellWidth(), sprite.getCellHeight()));

  }

  private static class SpriteTransition extends Transition {

    private final Sprite sprite;
    private final ChipView chipView;

    private int prevIndex = -1;

    /**
     * 
     * @param sprite
     * @param chipView
     */
    public SpriteTransition(Sprite sprite, ChipView chipView) {
      super();

      this.sprite = sprite;
      this.chipView = chipView;

      setCycleDuration(sprite.getDuration().divide(sprite.getNumberOfFrames()));

    }

    /**
     * @see javafx.animation.Transition#interpolate(double)
     */
    @Override
    protected void interpolate(double frac) {

      final int index = Math.min((int) Math.floor(frac * sprite.getNumberOfFrames()),
          sprite.getNumberOfFrames() - 1);

      if (index != prevIndex) {

        final double x = (index % sprite.getColumns())
            * /* chipView.getImage().getWidth() + */ sprite.getCellWidth();
        final double y = (index / sprite.getColumns())
            * /* chipView.getImage().getHeight() + */ sprite.getCellHeight();

        chipView.setViewport(new Rectangle2D(x, y, sprite.getCellWidth(), sprite.getCellHeight()));

        prevIndex = index;

      }

    }

  }

}
