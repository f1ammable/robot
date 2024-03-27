package com.coolteam;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class SensorManager {
  private static EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S2);
  private static EV3ColorSensor colour = new EV3ColorSensor(SensorPort.S1);
  private static NXTSoundSensor sound = new NXTSoundSensor(SensorPort.S4);
  private static EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S3);

  private static SampleProvider ambient = colour.getAmbientMode();
  private static SampleProvider angle = gyro.getRateMode();

  private static float[] touchSamples = new float[touch.sampleSize()];
  private static float[] lightSamples = new float[ambient.sampleSize()];
  private static float[] angleSamples = new float[angle.sampleSize()];

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
}
