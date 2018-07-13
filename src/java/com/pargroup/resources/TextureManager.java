package com.pargroup.resources;

import javafx.scene.CacheHint;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * @author Rawad Aboudlal
 *
 */
public class TextureManager {

  public static void applyTextureColour(ImageView imageView, String colour) {

    Color c = Color.web(colour);

    ColorAdjust monochrome = new ColorAdjust();
    monochrome.setBrightness(1);

    Blend blush = new Blend(BlendMode.SRC_ATOP, monochrome,
        new ColorInput(0, 0, imageView.getImage().getWidth(), imageView.getImage().getHeight(), c));

    imageView.setEffect(blush);

    imageView.setCache(true);
    imageView.setCacheHint(CacheHint.SPEED);

  }

}
