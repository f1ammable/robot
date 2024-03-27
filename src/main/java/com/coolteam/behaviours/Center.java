package com.coolteam.behaviours;

import com.coolteam.MotorManager;
import com.coolteam.SensorManager;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Center implements Behavior {
  private boolean needsCentering = true;
  private SensorManager s = new SensorManager();
  private MotorManager m = new MotorManager();

  private static final float LIGHT_THRESHOLD = 0.07f;

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

    if (s.getAmbient() > LIGHT_THRESHOLD && s.getTouch() != 1) {
      m.MoveX(35);
      m.MoveY(35);
    }
  }

  @Override
  public void suppress() {}
}
