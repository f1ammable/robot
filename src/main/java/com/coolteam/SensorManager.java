package com.coolteam;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class SensorManager {
  // Colour Sensor
  private static final EV3ColorSensor colour = new EV3ColorSensor(SensorPort.S1);
  private static final SampleProvider ambient = colour.getAmbientMode();
  private static final float[] lightSamples = new float[ambient.sampleSize()];

  // Gyro Sensor
  private static final EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S3);
  private static final SampleProvider angle = gyro.getRateMode();
  private static final float[] angleSamples = new float[angle.sampleSize()];

  public static int getShakeCount() {
    angle.fetchSample(angleSamples, 0);
    float gyroValue = angleSamples[0];

    if (gyroValue > 10) {
      return 3; // Simulate multiple shakes
    }

    return 0;
  }

  // Touch Sensor
  private static final EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S2);
  private static final float[] touchSamples = new float[touch.sampleSize()];

  // Sound Sensor
  private static final NXTSoundSensor mic = new NXTSoundSensor(SensorPort.S4);
  private static final SampleProvider volume = mic.getDBAMode();
  private static final float[] soundSamples = new float[volume.sampleSize()];

  public static void init() {
    gyro.reset();
  }

  public static int getTouch() {
    touch.fetchSample(touchSamples, 0);
    return (int) touchSamples[0];
  }

  public static float getAmbient() {
    ambient.fetchSample(lightSamples, 0);
    return lightSamples[0];
  }

  public static float getAngle() {
    gyro.fetchSample(angleSamples, 0);
    return angleSamples[0];
  }

  public static float getVolume() {
    volume.fetchSample(soundSamples, 0);
    return soundSamples[0];
  }
}
