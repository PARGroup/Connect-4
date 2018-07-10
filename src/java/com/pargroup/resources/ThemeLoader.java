package com.pargroup.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.pargroup.animation.Sprite;
import com.pargroup.view.BoardConfig;
import com.pargroup.view.theme.Theme;

/**
 * @author Rawad Aboudlal
 *
 */
public class ThemeLoader {

  private static final String THEME_FILE = "theme.txt";

  private static final String DATA_SEPARATOR = "=";

  /**
   * Use this method to load built-in themes.
   */
  static void loadThemes() {

    ThemeLoader.loadThemes(Paths.get("src/resources/themes/"));

  }

  static void loadThemes(Path pathToThemes) {

    File themesFolder = pathToThemes.toFile();

    for (File themeFolder : themesFolder.listFiles()) {

      Theme theme = ThemeLoader.loadTheme(themeFolder);

      TextureLoader.loadTextures(theme);

    }

  }

  private static Theme loadTheme(File themeFolder) {

    Theme theme = new Theme();
    BoardConfig boardConfig = new BoardConfig();
    Sprite sprite = new Sprite();

    theme.setFolder(themeFolder);

    theme.setBoardConfig(boardConfig);

    theme.setSprite(sprite);

    Mode mode = null;

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(ThemeLoader.getThemeFileInputStream(themeFolder)))) {

      String line = null;

      while ((line = reader.readLine()) != null) {

        Mode m = ThemeLoader.checkLineForMode(line);

        if (m != null) {
          mode = m;
          continue;
        }

        if (mode != null) {

          String[] data = line.split(DATA_SEPARATOR);

          if (data.length < 2) {
            continue;
          }

          String key = data[0];
          String value = data[1];

          switch (mode) {
            case GENERAL:
              ThemeLoader.parseGeneralLine(theme, key, value);
            case TEXTURES:
              TextureLoader.parseTextureLine(theme, key, value);
              break;
            case BOARD_CONFIG:
              BoardConfigLoader.parseBoardConfigLine(boardConfig, key, value);
              break;
            case SPRITE:
              SpriteLoader.parseSpriteLine(sprite, key, value);
              break;
          }

        } else {
          System.err.println("The following line in " + themeFolder.getPath()
              + " is not formatted properly:\n" + line);
        }

      }

      ThemeManager.addTheme(themeFolder.getName(), theme);

    } catch (IOException ex) {
      System.err.println("An error occured while loading the following theme: " + themeFolder);
      ex.printStackTrace();
    }

    return theme;

  }

  private static Mode checkLineForMode(String line) {

    for (Mode m : Mode.values()) {
      if (line.equals(m.getKey())) {
        return m;
      }
    }

    return null;

  }

  private static void parseGeneralLine(Theme theme, String key, String value) {

    if (key.equals("animation factory")) {
      theme.setChipPlacementAnimation(AnimationLoader.loadAnimationFactory(value));
    }

  }

  private static InputStream getThemeFileInputStream(File folder) throws FileNotFoundException {
    return new FileInputStream(folder.toPath().resolve(THEME_FILE).toFile());
  }

  private static enum Mode {

    GENERAL("General"), TEXTURES("Textures"), BOARD_CONFIG("Board Config"), SPRITE("Sprite");

    private final String key;

    /**
     * @param key
     */
    private Mode(String key) {
      this.key = key;
    }

    /**
     * @return the key
     */
    public String getKey() {
      return key;
    }

  }

}
