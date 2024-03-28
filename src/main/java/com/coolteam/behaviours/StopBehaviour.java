package com.coolteam.behaviours;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class StopBehaviour implements Behavior {
  @Override
  public boolean takeControl() {
    return Button.UP.isDown();
  }

  @Override
  public void action() {
    LCD.clear();
    while (Button.ENTER.isUp()) {
      try {
        LCD.drawString("Emergency Stop!", 0, 3);
        LCD.drawString("Press Enter to quit!", 0, 4);
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    System.exit(-1);
  }

  @Override
  public void suppress() {}
}
