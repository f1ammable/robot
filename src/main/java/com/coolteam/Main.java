package com.coolteam;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Main {
  public static void main(String[] args) {
    while (Button.ENTER.isUp()) {
      LCD.clear();
      LCD.drawString("Hello from Maven!", 0, 0);
    }
  }
}
