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

    boardConfig.setHgap(8);
    boardConfig.setVgap(8);

    boardConfig.setChipRadius(32);

    boardConfig.setChipWidth(64);
    boardConfig.setChipHeight(64);

    return boardConfig;

  }

}
