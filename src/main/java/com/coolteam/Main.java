package com.coolteam;

import com.coolteam.behaviours.Autostop;
import com.coolteam.behaviours.Center;
import com.coolteam.behaviours.StopBehaviour;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
  public static void main(String[] args) {

    MotorManager.init();
    SensorManager.init();

    LCD.clear();

    Arbitrator a =
        new Arbitrator(new Behavior[] {new Center(), new Autostop(), new StopBehaviour()});
    a.go();
  }
}
