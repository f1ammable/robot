package com.coolteam.behaviours;

import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class LowBatteryCheck implements Behavior {
  private volatile boolean suppressed = false;
  private static final double LOW_BATTERY_THRESHOLD =
      6.5; // Adjust based on your robot's requirements

  @Override
  public boolean takeControl() {
    return Battery.getVoltage() < LOW_BATTERY_THRESHOLD;
  }

  @Override
  public void action() {
    suppressed = false;

    // Display a low battery warning and advise on the next steps
    LCD.clear();
    LCD.drawString("Low Battery!", 2, 2);
    LCD.drawString("Please recharge", 2, 3);
    LCD.refresh();

    // Wait a bit to ensure the message is seen, without handling interruptions as complexly
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // If we're interrupted, just exit
    }
    System.exit(0);

    while (!suppressed && Battery.getVoltage() < LOW_BATTERY_THRESHOLD) {
      Thread.yield();
    }
  }

  @Override
  public void suppress() {
    suppressed = true;
  }
}
