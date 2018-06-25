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
import java.util.HashMap;
import com.pargroup.model.BoardConfig;
import com.pargroup.view.animation.Sprite;
import com.pargroup.view.theme.Theme;

/**
 * @author Rawad Aboudlal
 *
 */
public class ThemeLoader {

  public static final String DEFAULT_THEME = "default";
  public static final String PENCIL_THEME = "pencil_theme";

  private static final String THEME_FILE = "theme.txt";

  private static final String DATA_SEPARATOR = "=";

  private static final HashMap<String, Theme> THEMES = new HashMap<String, Theme>();

  /**
   * Use this method to load built-in themes.
   */
  public static void loadThemes() {

    ThemeLoader.loadThemes(Paths.get("src/resources/themes/"));

  }

  public static void loadThemes(Path pathToThemes) {

    File themesFolder = pathToThemes.toFile();

    for (File themeFolder : themesFolder.listFiles()) {

      Theme theme = ThemeLoader.loadTheme(themeFolder);

      TextureLoader.loadTextures(theme, themeFolder);

    }

  }

  private static Theme loadTheme(File themeFolder) {

    Theme theme = new Theme();
    BoardConfig boardConfig = new BoardConfig();
    Sprite sprite = new Sprite();

    theme.setChipColours(new String[2]);
    theme.setBoardConfig(boardConfig);

    theme.setSprite(sprite);

    Mode mode = null;

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(ThemeLoader.getThemeFileInputStream(themeFolder)))) {

      String line = null;

      while ((line = reader.readLine()) != null) {

        if (line.equals(Mode.TEXTURES.getKey())) {
          mode = Mode.TEXTURES;
        } else if (line.equals(Mode.BOARD_CONFIG.getKey())) {
          mode = Mode.BOARD_CONFIG;
        } else if (line.equals(Mode.SPRITE.getKey())) {
          mode = Mode.SPRITE;
        } else if (mode != null) {

          String[] data = line.split(DATA_SEPARATOR);

          if (data.length < 2) {
            continue;
          }

          String key = data[0];
          String value = data[1];

          switch (mode) {
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

      THEMES.put(themeFolder.getName(), theme);

    } catch (IOException ex) {
      System.err.println("An error occured while loading the following theme: " + themeFolder);
      ex.printStackTrace();
    }

    return theme;

  }

  private static InputStream getThemeFileInputStream(File folder) throws FileNotFoundException {
    return new FileInputStream(folder.toPath().resolve(THEME_FILE).toFile());
  }

  /**
   * Get the Theme object associated with the specified key.
   * 
   * @param key
   * @return
   */
  public static Theme getTheme(String key) {
    return THEMES.get(key);
  }

  private static enum Mode {

    TEXTURES("Textures"), BOARD_CONFIG("Board Config"), SPRITE("Sprite");

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
