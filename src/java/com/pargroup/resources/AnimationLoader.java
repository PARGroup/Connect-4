package com.pargroup.resources;

import com.pargroup.view.animation.DropAnimationFactory;
import com.pargroup.view.animation.SpriteAnimationFactory;

/**
 * @author Rawad Aboudlal
 *
 */
public class AnimationLoader {

  public static void loadAnimations() {

    new DropAnimationFactory();
    new SpriteAnimationFactory();

  }

}
