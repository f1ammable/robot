package com.coolteam.behaviours;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class WavePrinter implements Behavior {
  @Override
  public boolean takeControl() {
    // TODO: Set up sound sensor and activate if above certain level
    return Button.DOWN.isDown();
  }

  @Override
  public void action() {}

  @Override
  public void suppress() {}
}
