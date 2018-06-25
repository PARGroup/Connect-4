package com.pargroup.resources;

import com.pargroup.model.BoardConfig;

/**
 * @author Rawad Aboudlal
 *
 */
public class BoardConfigLoader {

  public static void parseBoardConfigLine(BoardConfig boardConfig, String key, String value) {

    int intValue = Integer.parseInt(value);

    if (key.equals("columns")) {
      boardConfig.setColumns(intValue);
    } else if (key.equals("rows")) {
      boardConfig.setRows(intValue);
    } else if (key.equals("boardWidth")) {
      boardConfig.setBoardWidth(intValue);
    } else if (key.equals("boardHeight")) {
      boardConfig.setBoardHeight(intValue);
    } else if (key.equals("chipRadius")) {
      boardConfig.setChipRadius(intValue);
    } else if (key.equals("chipWidth")) {
      boardConfig.setChipWidth(intValue);
    } else if (key.equals("chipHeight")) {
      boardConfig.setChipHeight(intValue);
    } else if (key.equals("hgap")) {
      boardConfig.setHgap(intValue);
    } else if (key.equals("vgap")) {
      boardConfig.setVgap(intValue);
    }

  }

}
