package com.pargroup.resources;

import com.pargroup.animation.ChipAnimationFactory;

/**
 * @author Rawad Aboudlal
 *
 */
public class AnimationLoader {

  public static ChipAnimationFactory loadAnimationFactory(String name) {

    try {

      Class<?> animationFactoryClass = ClassLoader.getSystemClassLoader().loadClass(name);

      return (ChipAnimationFactory) animationFactoryClass.getConstructor().newInstance();

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;

  }

}
