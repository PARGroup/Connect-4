package com.pargroup.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import com.pargroup.view.theme.Theme;
import javafx.scene.image.Image;

/**
 * @author Rawad Aboudlal
 *
 */
public class TextureLoader {

  public static final String BACKGROUND = "background";
  public static final String BOARD = "board";

  private static final HashMap<String, Image> TEXTURES = new HashMap<String, Image>();

  public static void parseTextureLine(Theme theme, String key, String value) {

    if (key.equals("board")) {
      theme.setBoardTexture(value);
    } else if (key.equals("background")) {
      theme.setBackgroundTexture(value);
    } else if (key.equals("chip1")) {
      theme.getChipColours()[0] = value;
    } else if (key.equals("chip2")) {
      theme.getChipColours()[1] = value;
    } else if (key.equals("animation factory")) {
      theme.setChipPlacementAnimation(value);
    }

  }

  public static void loadTextures(Theme theme, File themeFolder) {

    TextureLoader.loadTexture(BACKGROUND, theme.getBackgroundTexture(), themeFolder);
    TextureLoader.loadTexture(BOARD, theme.getBoardTexture(), themeFolder);

    String[] chipColours = theme.getChipColours();

    for (String chipColour : chipColours) {
      TextureLoader.loadTexture(chipColour, chipColour, themeFolder);
    }

  }

  private static void loadTexture(String key, String name, File folder) {

    if (name == null) {
      System.out.printf("The texture with key \"%s\" is null and will not be loaded.%n", key);
      return;
    }

    try {

      TEXTURES.put(key, new Image(new FileInputStream(folder.toPath().resolve(name).toFile())));

    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  public static Image getTexture(String key) {

    if (key == null) {
      return null;
    }

    return TEXTURES.get(key);

  }

}
