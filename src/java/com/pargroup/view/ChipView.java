package com.pargroup.view;

import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import com.pargroup.resources.ThemeManager;
import javafx.scene.image.ImageView;

/**
 * @author Rawad Aboudlal
 *
 */
public class ChipView extends ImageView implements ThemeChangeListener {

  private Chip chip;

  /**
   * @param chip
   */
  public ChipView(Chip chip) {
    super();

    this.chip = chip;

    updateTexture();

    ThemeManager.addThemeChangeListener(this);

  }

  /**
   * @see com.pargroup.event.listener.ThemeChangeListener#onThemeChange()
   */
  @Override
  public void onThemeChange() {

    ThemeManager.getAnimationFactory().resetViewport(this);

    updateTexture();

  }

  private void updateTexture() {
    setImage(TextureLoader.getChipTexture(chip.getOwner()));
  }

  /**
   * @return the chip
   */
  public Chip getChip() {
    return chip;
  }

}
