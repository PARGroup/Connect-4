package com.pargroup.view;

import com.pargroup.controller.UIController;
import com.pargroup.event.listener.ThemeChangeListener;
import com.pargroup.model.Board;
import com.pargroup.model.Chip;
import com.pargroup.resources.TextureLoader;
import com.pargroup.resources.ThemeManager;
import com.pargroup.view.theme.Theme;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author Rawad Aboudlal
 *
 */
public class BoardView extends StackPane implements ThemeChangeListener {

  private Board board;
  private Theme theme;
  private BoardConfig boardConfig;

  private PlacementIndicatorView placementIndicatorView;
  private Pane placementIndicatorPane;
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

    placementIndicatorView = new PlacementIndicatorView();
    placementIndicatorPane = new Pane();
    backgroundTexture = new ImageView();
    chipsPane = new Pane();
    boardTexture = new ImageView();
    clickPane = new Pane();

    placementIndicatorPane.getChildren().add(placementIndicatorView);

    updateTheme();

    VBox boardHolder = new VBox();
    boardHolder.setAlignment(Pos.CENTER);
    boardHolder.getChildren().add(placementIndicatorPane);

    StackPane boardAndChipsHolder = new StackPane();
    boardAndChipsHolder.getChildren().add(chipsPane);
    boardAndChipsHolder.getChildren().add(boardTexture);
    boardAndChipsHolder.getChildren().add(clickPane);

    boardHolder.getChildren().add(boardAndChipsHolder);

    getChildren().add(backgroundTexture);
    getChildren().add(boardHolder);

    ThemeManager.addThemeChangeListener(this);

  }

  public void chipPlaced(UIController uiController, Chip chip, int x, int startY, int endY) {

    ChipView chipView = new ChipView(chip);

    Animation chipPlacementAnimation =
        ThemeManager.getAnimationFactory().createAnimation(chipView, x, startY, endY);

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

    chipsPane.setMaxWidth(boardImage.getWidth());
    chipsPane.setMaxHeight(boardImage.getHeight());

    clickPane.setMaxWidth(boardImage.getWidth());
    clickPane.setMaxHeight(boardImage.getHeight());

    placementIndicatorPane.setMaxWidth(boardImage.getWidth());
    placementIndicatorPane.setMaxHeight(boardConfig.getChipRadius() * 2 + boardConfig.getVgap());
    placementIndicatorPane.setMinHeight(boardConfig.getChipRadius() * 2 + boardConfig.getVgap());

    // placementIndicatorPane.translateYProperty()
    // .bind(boardTexture.yProperty().subtract(placementIndicatorPane.heightProperty()));

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

  /**
   * @return the placementIndicatorView
   */
  public PlacementIndicatorView getPlacementIndicatorView() {
    return placementIndicatorView;
  }

}
