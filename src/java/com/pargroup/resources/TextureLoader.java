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

  private static final HashMap<String, Image> TEXTURES = new HashMap<String, Image>();

  public static Image getBackgroundTexture() {

    String name = ThemeManager.getCurrentTheme().getBackgroundTexture();

    return getTexture(name);

  }

  public static Image getBoardTexture() {

    String name = ThemeManager.getCurrentTheme().getBoardTexture();

    return getTexture(name);

  }

  public static Image getChipTexture(int chipIndex) {

    String name = ThemeManager.getCurrentTheme().getChipColours()[chipIndex];

    return getTexture(name);

  }

  static void parseTextureLine(Theme theme, String key, String value) {

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

  static void loadTextures(Theme theme) {

    File themeFolder = theme.getFolder();

    TextureLoader.loadTexture(theme.getBackgroundTexture(), themeFolder);
    TextureLoader.loadTexture(theme.getBoardTexture(), themeFolder);

    String[] chipColours = theme.getChipColours();

    for (String chipColour : chipColours) {
      TextureLoader.loadTexture(chipColour, themeFolder);
    }

  }

  private static void loadTexture(String name, File folder) {

    if (name == null) {
      System.out.printf("The texture file \"%s\" is null and will not be loaded.%n", name);
      return;
    }

    try {

      TEXTURES.put(TextureLoader.createKey(name, folder),
          new Image(new FileInputStream(folder.toPath().resolve(name).toFile())));

    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  private static Image getTexture(String name) {

    if (name == null) {
      return null;
    }

    return TEXTURES.get(TextureLoader.createKey(name));

  }

  private static Image getTexture(Theme theme, String name) {

    if (name == null) {
      return null;
    }

    return TEXTURES.get(TextureLoader.createKey(name, theme));

  }

  private static String createKey(String name) {
    return TextureLoader.createKey(name, ThemeManager.getCurrentTheme());
  }

  private static String createKey(String name, Theme theme) {
    return TextureLoader.createKey(name, theme.getFolder());
  }

  private static String createKey(String name, File folder) {
    return folder.toPath().getFileName().resolve(name).toString();
  }

}
