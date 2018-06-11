package com.pargroup.view.animation;

import java.util.HashMap;
import javafx.animation.Animation;
import javafx.scene.Node;

/**
 * @author Rawad Aboudlal
 *
 */
public abstract class AnimationFactory {

  private static final HashMap<String, AnimationFactory> FACTORIES =
      new HashMap<String, AnimationFactory>();

  private final String key;

  private Node node;

  private int x;

  private int startY;
  private int endY;

  /**
   * @param key
   */
  public AnimationFactory(String key) {
    super();

    this.key = key;

    FACTORIES.put(key, this);

  }

  public static Animation createAnimation(String key) {
    return FACTORIES.get(key).createAnimation();
  }

  protected abstract Animation createAnimation();

  /**
   * @return the key
   */
  public final String getKey() {
    return key;
  }

  /**
   * @return the node
   */
  public Node getNode() {
    return node;
  }

  /**
   * @param node the node to set
   */
  public void setNode(Node node) {
    this.node = node;
  }

  /**
   * @return the x
   */
  public int getX() {
    return x;
  }

  /**
   * @param x the x to set
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * @return the startY
   */
  public int getStartY() {
    return startY;
  }

  /**
   * @param startY the startY to set
   */
  public void setStartY(int startY) {
    this.startY = startY;
  }

  /**
   * @return the endY
   */
  public int getEndY() {
    return endY;
  }

  /**
   * @param endY the endY to set
   */
  public void setEndY(int endY) {
    this.endY = endY;
  }

}
