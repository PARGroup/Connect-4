package com.pargroup.resources;

import com.pargroup.animation.Sprite;
import javafx.util.Duration;

/**
 * @author Rawad Aboudlal
 *
 */
public class SpriteLoader {

  public static void parseSpriteLine(Sprite sprite, String key, String value) {

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

}
