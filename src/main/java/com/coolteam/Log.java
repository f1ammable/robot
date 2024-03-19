package com.coolteam;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Log {
  public static void error(String msg) {
    while (Button.ENTER.isUp()) {
      LCD.clear();
      LCD.drawString("ERROR!", 0, 3);
      LCD.drawString(msg, 0, 4);

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
