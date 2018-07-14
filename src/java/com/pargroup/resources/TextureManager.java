package com.pargroup.resources;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * @author Rawad Aboudlal
 *
 */
public class TextureManager {

  public static void applyTextureColour(ImageView imageView, String colour) {

    Color c = Color.web(colour);

    Image image = imageView.getImage();

    PixelReader pixelReader = image.getPixelReader();

    WritableImage newImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

    PixelWriter pixelWriter = newImage.getPixelWriter();

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {

        int argb = pixelReader.getArgb(i, j);

        int a = (argb >> 24) & 0xFF;
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = (argb >> 0) & 0xFF;

        int newR = Math.min((int) (c.getRed() * 255), r);
        int newG = Math.min((int) (c.getGreen() * 255), g);
        int newB = Math.min((int) (c.getBlue() * 255), b);

        int newArgb = (a << 24) | (newR << 16) | (newG << 8) | (newB << 0);

        pixelWriter.setArgb(i, j, newArgb);

      }
    }

    imageView.setImage(newImage);

  }

}
