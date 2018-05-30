package com.pargroup.resources;

import com.pargroup.model.BoardConfig;
import javafx.geometry.Insets;

/**
 * @author Rawad Aboudlal
 *
 */
public class ConfigsLoader {

  public static BoardConfig getBoardConfig() {

    BoardConfig boardConfig = new BoardConfig();

    boardConfig.setColumns(7);
    boardConfig.setRows(6);

    boardConfig.setBoardWidth(512);
    boardConfig.setBoardHeight(440);

    boardConfig.setHorizontalGap(8);
    boardConfig.setVerticalGap(8);
    boardConfig.setBoardInsets(new Insets(8, 0, 0, 8));

    boardConfig.setChipWidth(64);
    boardConfig.setChipHeight(64);

    return boardConfig;

  }

}
