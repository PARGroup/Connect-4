package com.pargroup.resources;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.pargroup.animation.ChipAnimationFactory;
import com.pargroup.event.listener.ThemeChangeListener;
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

    ThemeLoader.loadThemes();

    setTheme(DEFAULT_THEME);

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

  public static Theme getTheme(String key) {
    return THEMES.get(key);
  }

  public static void addThemeChangeListener(ThemeChangeListener listener) {
    THEME_CHANGE_LISTENERS.add(listener);
  }

  public static boolean removeThemeChangeListener(ThemeChangeListener listener) {
    return THEME_CHANGE_LISTENERS.remove(listener);
  }

  public static void clearThemeChangeListener() {
    THEME_CHANGE_LISTENERS.clear();
  }

  public static ChipAnimationFactory getAnimationFactory() {
    return currentTheme.getChipPlacementAnimation();
  }

  /**
   * @return the currentTheme
   */
  public static Theme getCurrentTheme() {
    return currentTheme;
  }

  static String createKey(String name, Theme theme) {
    return ThemeManager.createKey(name, theme.getFolder());
  }

  static String createKey(String name, File folder) {
    return folder.toPath().getFileName().resolve(name).toString();
  }

}
