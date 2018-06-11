package com.pargroup.resources;

import java.io.InputStream;
import java.util.HashMap;
import com.pargroup.model.ChipColour;
import javafx.scene.image.Image;

/**
 * @author Rawad Aboudlal
 *
 */
public class TextureLoader {

  public static final String BOARD = "board";

  private static final HashMap<String, Image> TEXTURES = new HashMap<String, Image>();

  public static void loadTextures() {

    TEXTURES.put(BOARD, new Image(TextureLoader.getTextureAsStream("board-trans-yellow")));
    TEXTURES.put(ChipColour.RED.getChipName(),
        new Image(TextureLoader.getTextureAsStream(ChipColour.RED.getChipName())));
    TEXTURES.put(ChipColour.YELLOW.getChipName(),
        new Image(TextureLoader.getTextureAsStream(ChipColour.YELLOW.getChipName())));

  }

  private static InputStream getTextureAsStream(String name) {
    return TextureLoader.class.getClassLoader().getResourceAsStream(name + ".png");
  }

  public static Image getTexture(String key) {
    return TEXTURES.get(key);
  }

}
