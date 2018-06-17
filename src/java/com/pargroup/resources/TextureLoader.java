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

    TextureLoader.loadTexture(BOARD, theme.getBoardTexture());

    ChipColour[] chipColours = theme.getChipColours();

    for (ChipColour chipColour : chipColours) {
      TextureLoader.loadTexture(chipColour.getChipName(), chipColour.getChipName());
    }

  }

  private static void loadTexture(String key, String name) {
    TEXTURES.put(key, new Image(TextureLoader.getTextureAsStream(name, "png")));
  }

  private static InputStream getTextureAsStream(String name, String extension) {
    return TextureLoader.class.getClassLoader().getResourceAsStream(name + "." + extension);
  }

  public static Image getTexture(String key) {
    return TEXTURES.get(key);
  }

}
