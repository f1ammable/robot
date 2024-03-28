package com.coolteam.behaviours;

import com.coolteam.MorsePress;
import com.coolteam.MorseType;
import com.coolteam.MotorManager;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class MorseCode implements Behavior {
  MorsePress p = new MorsePress();
  private boolean shouldRun = false;
  private int pressCount = 0;

  @Override
  public boolean takeControl() {
    LCD.clear();
    try {
      LCD.drawString("Morse Code", 0, 3);
      if (Button.RIGHT.isDown()) {
        pressCount++;
        Delay.msDelay(100); // Added a small delay to avoid multiple counts for a single press
      }

      if (pressCount > 4) {
        shouldRun = true;
      }

      Thread.sleep(100);
      return shouldRun;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void action() {
    long end = System.currentTimeMillis() + 3000;
    while (System.currentTimeMillis() < end) {
      if (Button.RIGHT.isDown()) {
        long pressTime = System.currentTimeMillis();

        while (Button.RIGHT.isDown()) {
          // Pause here for better long press detection
        }

        long pressDuration = System.currentTimeMillis() - pressTime;
        if (pressDuration >= 250) { // Changed to 500ms for long press
          p.AddPress(MorseType.LONG);
        } else {
          p.AddPress(MorseType.SHORT);
        }
      }

      try {
        LCD.drawString("Listening for touches!", 0, 3);
        Thread.sleep(25);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    LCD.clear();

    // Draw the Morse code
    for (int x : p.toIntArray()) {
      // MotorManager.MovePen(false); // Ensure pen is down
      if (x == 0) {
        // Draw a dot
        MotorManager.MovePen(false);
        MotorManager.MoveY(5); // Adjust the distance as needed
        MotorManager.MovePen(false);
      } else if (x == 1) {
        // Draw a long line
        MotorManager.MovePen(false);
        MotorManager.MoveY(30); // Adjust the distance as needed
        MotorManager.MovePen(false);
      }

      MotorManager.MoveY(100); // Move to the next position, leaving space between symbols
    }

    while (Button.ENTER.isUp()) {
      LCD.drawString("Morse Code finished!", 0, 4);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    shouldRun = false;
  }

  @Override
  public void suppress() {
    shouldRun = false;
  }
}
