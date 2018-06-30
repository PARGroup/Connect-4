package com.pargroup.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.pargroup.view.theme.Theme;
import javafx.scene.image.Image;

/**
 * @author Rawad Aboudlal
 *
 */
public class TextureLoader {

  private static final HashMap<String, Image> TEXTURES = new HashMap<String, Image>();

  private static final Pattern CHIP_TEXTURE_PATTERN = Pattern.compile("(?<texture>.+?)(,|$) *");

  public static Image getBackgroundTexture() {
    return getBackgroundTexture(ThemeManager.getCurrentTheme());
  }

  public static Image getBackgroundTexture(Theme theme) {
    return getTexture(theme, theme.getBackgroundTexture());
  }

  public static Image getBoardTexture() {
    return getBoardTexture(ThemeManager.getCurrentTheme());
  }

  public static Image getBoardTexture(Theme theme) {
    return getTexture(theme, theme.getBoardTexture());
  }

  public static Image getChipTexture(int chipIndex) {
    return getChipTexture(ThemeManager.getCurrentTheme(), chipIndex);
  }

  public static Image getChipTexture(Theme theme, int chipIndex) {
    return getTexture(theme, theme.getChipColours().get(chipIndex));
  }

  static void parseTextureLine(Theme theme, String key, String value) {

    if (key.equals("board")) {
      theme.setBoardTexture(value);
    } else if (key.equals("background")) {
      theme.setBackgroundTexture(value);
    } else if (key.equals("chips")) {

      Matcher matcher = CHIP_TEXTURE_PATTERN.matcher(value);

      theme.setChipColours(new ArrayList<String>());

      while (matcher.find()) {
        String chipTexture = matcher.group("texture");
        theme.getChipColours().add(chipTexture);
      }

      theme.setChipColours(Collections.unmodifiableList(theme.getChipColours()));

    }

  }

  static void loadTextures(Theme theme) {

    File themeFolder = theme.getFolder();

    TextureLoader.loadTexture(theme.getBackgroundTexture(), themeFolder);
    TextureLoader.loadTexture(theme.getBoardTexture(), themeFolder);

    for (String chipColour : theme.getChipColours()) {
      TextureLoader.loadTexture(chipColour, themeFolder);
    }

  }

  private static void loadTexture(String name, File folder) {

    if (name == null) {
      System.out.printf("The texture file \"%s\" is null and will not be loaded.%n", name);
      return;
    }

    try {

      TEXTURES.put(ThemeManager.createKey(name, folder),
          new Image(new FileInputStream(folder.toPath().resolve(name).toFile())));

    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  private static Image getTexture(Theme theme, String name) {

    if (name == null) {
      return null;
    }

    return TEXTURES.get(ThemeManager.createKey(name, theme));

  }

}
