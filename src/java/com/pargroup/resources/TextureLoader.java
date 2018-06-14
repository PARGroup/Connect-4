package com.pargroup.resources;

import java.io.InputStream;
import java.util.HashMap;
import com.pargroup.model.ChipColour;
import com.pargroup.view.theme.Theme;
import javafx.scene.image.Image;

/**
 * @author Rawad Aboudlal
 *
 */
public class TextureLoader {

  public static final String BOARD = "board";

  private static final HashMap<String, Image> TEXTURES = new HashMap<String, Image>();

  public static void loadTextures(Theme theme) {

    TEXTURES.put(BOARD, new Image(TextureLoader.getTextureAsStream(theme.getBoardTexture())));

    ChipColour[] chipColours = theme.getChipColours();

    for (ChipColour chipColour : chipColours) {
      TEXTURES.put(chipColour.getChipName(),
          new Image(TextureLoader.getTextureAsStream(chipColour.getChipName())));
    }

  }

  private static InputStream getTextureAsStream(String name) {
    return TextureLoader.class.getClassLoader().getResourceAsStream(name + ".png");
  }

  public static Image getTexture(String key) {
    return TEXTURES.get(key);
  }

}
