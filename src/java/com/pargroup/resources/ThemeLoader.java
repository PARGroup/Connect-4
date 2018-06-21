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
import com.pargroup.view.animation.ChipAnimationFactory;
import com.pargroup.view.animation.Sprite;
import com.pargroup.view.theme.Theme;
import javafx.util.Duration;

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
              ThemeLoader.parseTextureLine(theme, key, value);
              break;
            case BOARD_CONFIG:
              ThemeLoader.parseBoardConfigLine(boardConfig, key, value);
              break;
            case SPRITE:
              ThemeLoader.parseSpriteLine(sprite, key, value);
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
    }

    return theme;

  }

  private static void parseTextureLine(Theme theme, String key, String value) {

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

  private static void parseBoardConfigLine(BoardConfig boardConfig, String key, String value) {

    int intValue = Integer.parseInt(value);

    if (key.equals("columns")) {
      boardConfig.setColumns(intValue);
    } else if (key.equals("rows")) {
      boardConfig.setRows(intValue);
    } else if (key.equals("boardWidth")) {
      boardConfig.setBoardWidth(intValue);
    } else if (key.equals("boardHeight")) {
      boardConfig.setBoardHeight(intValue);
    } else if (key.equals("chipRadius")) {
      boardConfig.setChipRadius(intValue);
    } else if (key.equals("chipWidth")) {
      boardConfig.setChipWidth(intValue);
    } else if (key.equals("chipHeight")) {
      boardConfig.setChipHeight(intValue);
    } else if (key.equals("hgap")) {
      boardConfig.setHgap(intValue);
    } else if (key.equals("vgap")) {
      boardConfig.setVgap(intValue);
    }

  }

  private static void parseSpriteLine(Sprite sprite, String key, String value) {

    if (key.equals("duration")) {
      sprite.setDuration(Duration.valueOf(value));
      return;
    }

    int intValue = Integer.parseInt(value);

    if (key.equals("numberOfFrames")) {
      sprite.setNumberOfFrames(intValue);
    } else if (key.equals("columns")) {
      sprite.setColumns(intValue);
    } else if (key.equals("cellWidth")) {
      sprite.setCellWidth(intValue);
    } else if (key.equals("cellHeight")) {
      sprite.setCellHeight(intValue);
    }

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
