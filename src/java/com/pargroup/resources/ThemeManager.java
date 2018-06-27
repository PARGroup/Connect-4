package com.pargroup.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.view.animation.ChipAnimationFactory;
import com.pargroup.view.theme.Theme;

/**
 * @author Rawad Aboudlal
 *
 */
public class ThemeManager {

  public static final String DEFAULT_THEME = "default";
  public static final String PENCIL_THEME = "pencil_theme";

  private static final HashMap<String, Theme> THEMES = new HashMap<String, Theme>();

  private static final List<ThemeChangeListener> THEME_CHANGE_LISTENERS =
      new LinkedList<ThemeChangeListener>();

  private static Theme currentTheme;

  public static void initializeThemes() {

    AnimationLoader.loadAnimations();

    ThemeLoader.loadThemes();

  }

  public static void setTheme(String key) {

    currentTheme = THEMES.get(key);

    updateListeners();

  }

  private static void updateListeners() {

    for (ThemeChangeListener listener : THEME_CHANGE_LISTENERS) {
      listener.onThemeChange();
    }

  }

  static void addTheme(String key, Theme theme) {
    THEMES.put(key, theme);
  }

  public static void addThemeChangeListener(ThemeChangeListener listener) {
    THEME_CHANGE_LISTENERS.add(listener);
  }

  public static ChipAnimationFactory getAnimationFactory() {
    return ChipAnimationFactory.getAnimationFactory(currentTheme.getChipPlacementAnimation());
  }

  /**
   * @return the currentTheme
   */
  public static Theme getCurrentTheme() {
    return currentTheme;
  }

}
