package com.pargroup.resources;

import com.pargroup.model.BoardConfig;
import com.pargroup.model.ChipColour;
import com.pargroup.view.animation.DropAnimationFactory;
import com.pargroup.view.theme.Theme;

/**
 * @author Rawad Aboudlal
 *
 */
public class ThemeLoader {

  public static Theme getDefaultTheme() {

    ChipColour[] chipColours = new ChipColour[] {ChipColour.RED, ChipColour.BLUE,};

    BoardConfig boardConfig = ConfigsLoader.getBoardConfig();

    return new Theme(chipColours, DropAnimationFactory.KEY, "", "board-trans-yellow", boardConfig);

  }

}
