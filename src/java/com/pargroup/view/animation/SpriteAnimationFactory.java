package com.pargroup.view.animation;

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

  public static final String KEY = "SpriteAnimationFactory";

  private String pencilTexture;

  /**
   * 
   */
  public SpriteAnimationFactory() {
    super(KEY);
  }

  /**
   * @see com.pargroup.view.animation.ChipAnimationFactory#createAnimation()
   */
  @Override
  protected Animation createAnimation() {

    ParallelTransition animation = new ParallelTransition();

    TranslateTransition placementTransition =
        new TranslateTransition(Duration.millis(0), getChipView());

    placementTransition.setFromX(getX());
    placementTransition.setToX(getX());
    placementTransition.setFromY(getStartY());
    placementTransition.setToY(getEndY());

    if (getSprite() == null) {
      System.err.println(
          "The sprite to be used with this SpriteAnimationFactory has not been specified.");
    } else {

      SpriteTransition spriteTransition = new SpriteTransition(getSprite(), getChipView());

      animation.getChildren().add(spriteTransition);

    }

    animation.getChildren().add(placementTransition);

    return animation;

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

  /**
   * @return the pencilTexture
   */
  public String getPencilTexture() {
    return pencilTexture;
  }

  /**
   * @param pencilTexture the pencilTexture to set
   */
  public void setPencilTexture(String pencilTexture) {
    this.pencilTexture = pencilTexture;
  }

}
