package com.coolteam.demos;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class FollowLine {
  public FollowLine() {
    // Set up colour sensor
    EV3ColorSensor s = new EV3ColorSensor(SensorPort.S1);
    SampleProvider sensor = s.getRedMode();
    float[] level = new float[sensor.sampleSize()];
    float max = Float.MAX_VALUE;
    float min = Float.MIN_VALUE;

    // Set up motors
    BaseRegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.A);
    BaseRegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.B);
    left.synchronizeWith(new BaseRegulatedMotor[] {right});
    left.setSpeed(500);
    right.setSpeed(500);

    float LIGHT_AVERAGE = max / min;

    while (Button.ENTER.isUp()) {
      float current = level[0];
      sensor.fetchSample(level, 0);

      LCD.clear();
      LCD.drawString(String.format("Max: %f", max), 0, 0);
      LCD.drawString(String.format("Min: %f", min), 0, 1);

      if (current > LIGHT_AVERAGE) {
        left.forward();
        right.stop();
      } else {
        left.stop();
        right.forward();
      }

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    s.close();
    left.stop();
    right.stop();
    left.close();
    right.close();
  }
}
