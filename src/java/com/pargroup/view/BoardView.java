package com.pargroup.view;

import com.pargroup.controller.UIController;
import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.model.Board;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import com.pargroup.resources.ThemeManager;
import com.pargroup.view.animation.ChipAnimationFactory;
import com.pargroup.view.theme.Theme;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * @author Rawad Aboudlal
 *
 */
public class BoardView extends StackPane implements ThemeChangeListener {

  private Board board;
  private Theme theme;
  private BoardConfig boardConfig;

  private ImageView backgroundTexture;
  private Pane chipsPane;
  private ImageView boardTexture;
  private Pane clickPane;

  /**
   * @param board
   */
  public BoardView(Board board) {
    super();

    this.board = board;

    backgroundTexture = new ImageView();
    chipsPane = new Pane();
    boardTexture = new ImageView();
    clickPane = new Pane();

    updateTheme();

    getChildren().add(backgroundTexture);
    getChildren().add(chipsPane);
    getChildren().add(boardTexture);
    getChildren().add(clickPane);

    ThemeManager.addThemeChangeListener(this);

  }

  public void chipPlaced(UIController uiController, Chip chip, int x, int startY, int endY) {

    chip.setColour(theme.getChipColours()[chip.getOwner().getTurnIndex()]);

    ChipView chipView = new ChipView(chip);

    Animation chipPlacementAnimation = ChipAnimationFactory
        .createAnimation(theme.getChipPlacementAnimation(), chipView, x, startY, endY);

    chipPlacementAnimation.setOnFinished(new EventHandler<ActionEvent>() {

      /**
       * @see javafx.event.EventHandler#handle(javafx.event.Event)
       */
      @Override
      public void handle(ActionEvent event) {
        uiController.onChipPlaced(chipView, x, endY);
      }

    });

    chipsPane.getChildren().add(chipView);
    chipPlacementAnimation.playFromStart();

  }

  /**
   * @see com.pargroup.event.listener.ThemeChangeListener#onThemeChange()
   */
  @Override
  public void onThemeChange() {

    Platform.runLater(new Runnable() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        updateTheme();
      }
    });

  }

  private void updateTheme() {

    this.theme = ThemeManager.getCurrentTheme();
    this.boardConfig = theme.getBoardConfig();

    Image backgroundImage = TextureLoader.getBackgroundTexture();
    Image boardImage = TextureLoader.getBoardTexture();

    backgroundTexture.setImage(backgroundImage);
    boardTexture.setImage(boardImage);

    chipsPane.maxWidthProperty().bind(boardImage.widthProperty());
    chipsPane.maxHeightProperty().bind(boardImage.heightProperty());

    clickPane.maxWidthProperty().bind(boardImage.widthProperty());
    clickPane.maxHeightProperty().bind(boardImage.heightProperty());

  }

  /**
   * @return the clickPane
   */
  public Pane getClickPane() {
    return clickPane;
  }

  /**
   * @return the boardConfig
   */
  public BoardConfig getBoardConfig() {
    return boardConfig;
  }

}
