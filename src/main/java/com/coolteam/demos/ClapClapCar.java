package com.coolteam.demos;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class ClapClapCar {
  public ClapClapCar() {
    NXTSoundSensor soundSensor = new NXTSoundSensor(SensorPort.S2);
    SampleProvider soundProvider = soundSensor.getDBAMode();
    float[] level = new float[soundProvider.sampleSize()];

    float maxSoundLevel = Float.MIN_VALUE;
    float minSoundLevel = Float.MAX_VALUE;

    while (Button.ENTER.isUp()) {
      soundProvider.fetchSample(level, 0);
      float currentSoundLevel = level[0];
      LCD.clear();
      LCD.drawString("Max: " + maxSoundLevel, 0, 0);
      LCD.drawString("Min: " + minSoundLevel, 0, 1);
      LCD.drawString("Current " + currentSoundLevel, 0, 2);

      // Update max and min sound levels
      if (currentSoundLevel > maxSoundLevel) {
        maxSoundLevel = currentSoundLevel;
      }
      if (currentSoundLevel < minSoundLevel) {
        minSoundLevel = currentSoundLevel;
      }

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    soundSensor.close();
  }
}
