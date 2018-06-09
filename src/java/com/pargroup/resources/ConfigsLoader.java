package com.pargroup.resources;

import com.pargroup.model.BoardConfig;

/**
 * @author Rawad Aboudlal
 *
 */
public class ConfigsLoader {

  public static BoardConfig getBoardConfig() {

    BoardConfig boardConfig = new BoardConfig();

    boardConfig.setColumns(7);
    boardConfig.setRows(6);

    boardConfig.setBoardWidth(504);
    boardConfig.setBoardHeight(440);

    boardConfig.setHorizontalGap(4);
    boardConfig.setVerticalGap(4);

    boardConfig.setChipWidth(64);
    boardConfig.setChipHeight(64);

    return boardConfig;

  }

}
