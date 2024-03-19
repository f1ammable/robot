package com.coolteam;

import lejos.robotics.subsumption.Behavior;

public class Picasso implements Behavior {
  @Override
  public boolean takeControl() {
    // TODO: Use motion sensor to detect movement, if true then start drawing shit
    return false;
  }

  @Override
  public void action() {
    // TODO: Use `ImageScanner` to set up photo to draw
  }

  @Override
  public void suppress() {}
}
