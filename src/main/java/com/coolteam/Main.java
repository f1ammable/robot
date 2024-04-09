package com.coolteam;

import com.coolteam.behaviours.*;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
  public static void main(String[] args) {
    LCD.clear();

    while (Button.ENTER.isUp()) {
      LCD.drawString("Legionnaire Robot", 0, 2);
      LCD.drawString("Gabriel & Harry", 0, 3);
      LCD.drawString("Kasper & Artem", 0, 4);
      LCD.drawString("Version 0.9-alpha", 0, 5);

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    LCD.clear();

    Arbitrator a =
        new Arbitrator(
            new Behavior[] {
              new MorseCode(), new EtcherSketcher(), new Center(), new LowBatteryCheck(),
              // TODO: Calibrate gyro to differentiate between aggressive and light shaking
              // new StopBehaviour()
            });
    a.go();
  }
}
