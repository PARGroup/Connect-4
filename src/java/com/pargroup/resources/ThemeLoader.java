package com.pargroup.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.pargroup.model.BoardConfig;
import com.pargroup.model.ChipColour;
import com.pargroup.view.animation.ChipAnimationFactory;
import com.pargroup.view.animation.DropAnimationFactory;
import com.pargroup.view.animation.Sprite;
import com.pargroup.view.animation.SpriteAnimationFactory;
import com.pargroup.view.theme.Theme;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class ThemeLoader {

  private static final String DEFAULT_THEME = "default";

  private static final String THEME_FILE = "theme.txt";

  private static final String DATA_SEPARATOR = "=";

  /**
   * Use this method to load built-in themes.
   */
  public static void loadThemes() {
    ThemeLoader.loadThemes(Paths.get("themes/"));
  }

  public static void loadThemes(Path pathToThemes) {

    File themesFolder = new File(pathToThemes.toUri());

    for (File themeFolder : themesFolder.listFiles()) {
      ThemeLoader.loadTheme(themeFolder);
    }

  }

  private static Theme loadTheme(File themeFolder) {

    Theme theme = new Theme();
    BoardConfig boardConfig = new BoardConfig();

    theme.setBoardConfig(boardConfig);

    Sprite sprite = new Sprite();

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

    } catch (IOException ex) {
      System.err.println("An error occured while loading the following theme: " + themeFolder);
    }

    return theme;

  }

  private static void parseTextureLine(Theme theme, String key, String value) {

  }

  private static void parseBoardConfigLine(BoardConfig boardConfig, String key, String value) {

  }

  private static void parseSpriteLine(Sprite sprite, String key, String value) {

  }

  private static InputStream getThemeFileInputStream(File folder) {
    return ThemeLoader.class.getClassLoader()
        .getResourceAsStream(Paths.get(folder.getPath()).resolve(THEME_FILE).toString());
  }

  public static Theme getDefaultTheme() {

    String[] chipColours =
        new String[] {ChipColour.RED.getChipName(), ChipColour.BLUE.getChipName()};

    BoardConfig boardConfig = ConfigsLoader.getBoardConfig();

    return new Theme(chipColours, DropAnimationFactory.KEY, "", "board-trans-yellow", boardConfig);

  }

  public static Theme getSpriteTheme() {

    String[] chipColours =
        new String[] {ChipColour.RED_SPRITE.getChipName(), ChipColour.BLUE_SPRITE.getChipName()};

    BoardConfig boardConfig = ConfigsLoader.getBoardConfig();

    // SpriteLoader maybe? We need to get these values from the file somehow.
    // For Duration we can use toString() and valueOf()
    Sprite sprite = new Sprite(Duration.millis(1000), 8, 3, 64, 64);

    ((SpriteAnimationFactory) ChipAnimationFactory.getAnimationFactory(SpriteAnimationFactory.KEY))
        .setSprite(sprite);

    return new Theme(chipColours, SpriteAnimationFactory.KEY, "", "board-trans-yellow",
        boardConfig);

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
