package com.coolteam.behaviours;

import com.coolteam.SensorManager;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Autostop implements Behavior {

  @Override
  public boolean takeControl() {
    return SensorManager.getAngle() > 3 || SensorManager.getAngle() < -3;
  }

  @Override
  public void action() {
    LCD.clear();
    while (Button.ENTER.isUp()) {
      try {
        LCD.drawString("Printer has been moved! Press Enter to exit", 0, 3);
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void suppress() {}
}
