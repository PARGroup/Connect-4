package com.pargroup.view;

import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import javafx.scene.image.ImageView;

/**
 * @author Rawad Aboudlal
 *
 */
public class ChipView extends ImageView {

  private Chip chip;

  /**
   * @param chip
   */
  public ChipView(Chip chip) {
    super();

    this.chip = chip;

    this.setImage(TextureLoader.getTexture(chip.getColour().getChipName()));

  }

  /**
   * @return the chip
   */
  public Chip getChip() {
    return chip;
  }

}
