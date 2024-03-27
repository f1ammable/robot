package com.coolteam.behaviours;

import lejos.robotics.subsumption.Behavior;

public class MorseCode implements Behavior {
  @Override
  public boolean takeControl() {
    return false;
  }

  @Override
  public void action() {}

  @Override
  public void suppress() {}
}
