package com.pargroup.view;

import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.model.Player;
import com.pargroup.resources.TextureLoader;
import com.pargroup.resources.TextureManager;
import com.pargroup.resources.ThemeManager;
import javafx.scene.image.ImageView;

/**
 * @author Rawad Aboudlal
 *
 */
public class PlacementIndicatorView extends ImageView implements ThemeChangeListener {

  private Player currentPlayer;

  /**
   * 
   */
  public PlacementIndicatorView() {
    super();

    ThemeManager.addThemeChangeListener(this);

  }

  /**
   * @param currentPlayer the currentPlayer to set
   */
  public void setCurrentPlayer(Player currentPlayer) {

    this.currentPlayer = currentPlayer;

    updateTexture();

  }

  /**
   * @see com.pargroup.event.listener.ThemeChangeListener#onThemeChange()
   */
  @Override
  public void onThemeChange() {
    updateTexture();
  }

  private void updateTexture() {

    if (currentPlayer == null) {
      setImage(null);
    } else {
      setImage(TextureLoader.getIndicatorTexture());
      TextureManager.applyTextureColour(this, currentPlayer.getColour());
    }

  }

}
