package com.pargroup.resources;

import java.util.HashMap;
import com.pargroup.view.theme.Theme;

/**
 * @author Rawad Aboudlal
 *
 */
public class ThemeManager {

  public static final String DEFAULT_THEME = "default";
  public static final String PENCIL_THEME = "pencil_theme";

  private static final HashMap<String, Theme> THEMES = new HashMap<String, Theme>();

  private static Theme currentTheme;

  public static void initializeThemes() {

    AnimationLoader.loadAnimations();

    ThemeLoader.loadThemes();

  }

  public static void setTheme(String key) {
    currentTheme = THEMES.get(key);
  }

  static void addTheme(String key, Theme theme) {
    THEMES.put(key, theme);
  }

  /**
   * @return the currentTheme
   */
  public static Theme getCurrentTheme() {
    return currentTheme;
  }

}
