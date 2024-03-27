package com.coolteam.behaviours;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class StopBehaviour implements Behavior {
  @Override
  public boolean takeControl() {
    return Button.ENTER.isDown();
  }

  @Override
  public void action() {
    System.exit(-1);
  }

  @Override
  public void suppress() {}
}
