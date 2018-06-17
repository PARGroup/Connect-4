package com.pargroup.resources;

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

  public static Theme getDefaultTheme() {

    ChipColour[] chipColours = new ChipColour[] {ChipColour.RED, ChipColour.BLUE};

    BoardConfig boardConfig = ConfigsLoader.getBoardConfig();

    return new Theme(chipColours, DropAnimationFactory.KEY, "", "board-trans-yellow", boardConfig);

  }

  public static Theme getSpriteTheme() {

    ChipColour[] chipColours = new ChipColour[] {ChipColour.RED_SPRITE, ChipColour.BLUE_SPRITE};

    BoardConfig boardConfig = ConfigsLoader.getBoardConfig();

    // SpriteLoader maybe? We need to get these values from the file somehow.
    // For Duration we can use toString() and valueOf()
    Sprite sprite = new Sprite(Duration.millis(1000), 8, 3, 64, 64);

    ((SpriteAnimationFactory) ChipAnimationFactory.getAnimationFactory(SpriteAnimationFactory.KEY))
        .setSprite(sprite);

    return new Theme(chipColours, SpriteAnimationFactory.KEY, "", "board-trans-yellow",
        boardConfig);

  }

}
