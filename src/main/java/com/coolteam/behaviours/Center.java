package com.coolteam.behaviours;

import com.coolteam.MotorManager;
import com.coolteam.SensorManager;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Center implements Behavior {
  private boolean needsCentering = true;

  private static final float LIGHT_THRESHOLD = 0.1f;

  public Center() {}

  @Override
  public boolean takeControl() {
    return needsCentering;
  }

  @Override
  public void action() {

    try {
      LCD.drawString("Not aligned!", 0, 3);
      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    if (SensorManager.getAmbient() > LIGHT_THRESHOLD) MotorManager.MoveY(50);

    if (SensorManager.getTouch() != 1) MotorManager.MoveX(75);

    if (SensorManager.getAmbient() < LIGHT_THRESHOLD && SensorManager.getTouch() == 1) {
      LCD.clear();
      needsCentering = false;
    }
  }

  @Override
  public void suppress() {}
}
