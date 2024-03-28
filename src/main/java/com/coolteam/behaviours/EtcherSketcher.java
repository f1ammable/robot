package com.coolteam.behaviours;

import com.coolteam.MotorManager;
import com.coolteam.SensorManager;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EtcherSketcher implements Behavior {
  private boolean suppressed = false;
  private static final int MOVEMENT_SPEED = 50; // Adjust the speed value as needed
  private static final int MOVEMENT_DELAY = 50; // Adjust the delay value as needed

  @Override
  public boolean takeControl() {
    LCD.clear();
    try {
      LCD.drawString("Etch a Sketch!", 0, 3);
      Thread.sleep(100);
      return SensorManager.getShakeCount() >= 3;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void action() {
    suppressed = false;
    while (!suppressed) {
      int button = Button.readButtons();
      if ((button & Button.ID_UP) != 0) {
        MotorManager.MoveY(MOVEMENT_SPEED);
      } else if ((button & Button.ID_DOWN) != 0) {
        MotorManager.MoveY(-MOVEMENT_SPEED);
      } else if ((button & Button.ID_LEFT) != 0) {
        MotorManager.MoveX(-MOVEMENT_SPEED);
      } else if ((button & Button.ID_RIGHT) != 0) {
        MotorManager.MoveX(MOVEMENT_SPEED);
      } else {
        MotorManager.StopX();
        MotorManager.StopY();
      }

      if ((button & Button.ID_ENTER) != 0) {
        MotorManager.MovePen(false);
        Delay.msDelay(500);
      }

      Delay.msDelay(MOVEMENT_DELAY);
    }
  }

  @Override
  public void suppress() {
    suppressed = true;
    MotorManager.StopX();
    MotorManager.StopY();
  }
}
