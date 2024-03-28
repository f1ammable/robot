package com.coolteam.behaviours;

import com.coolteam.MotorManager;
import com.coolteam.SensorManager;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Center implements Behavior {
  private boolean needsCentering = true;

  private static final float LIGHT_THRESHOLD = SensorManager.getAmbient() * 0.8f;

  public Center() {}

  private void centerPen() {
    while (SensorManager.getTouch() != 1) {
      MotorManager.MoveIndefiniteX();
      // Check button state continuously without delay
    }
    MotorManager.StopX();
  }

  private void centerPaper() {
    while (SensorManager.getAmbient() > LIGHT_THRESHOLD) {
      MotorManager.MoveInverseY();
      // Check light sensor continuously without delay
    }
    MotorManager.StopY();
  }

  private void calibratePen() {
    LCD.clear();
    try {
      LCD.drawString("Press UP/DOWN for motor pos!", 0, 3);

      Delay.msDelay(3500);
      if (Button.UP.isDown()) MotorManager.MovePen(true);

      Thread.sleep(100);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean takeControl() {
    return needsCentering;
  }

  @Override
  public void action() {
    centerPen();
    centerPaper();
    // calibratePen();
    needsCentering = false;
  }

  @Override
  public void suppress() {}
}
