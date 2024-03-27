package com.coolteam;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;

public class SensorManager {
  private EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S2);
  public EV3ColorSensor colour = new EV3ColorSensor(SensorPort.S1);
  public NXTSoundSensor sound = new NXTSoundSensor(SensorPort.S3);

  private SampleProvider ambient = colour.getAmbientMode();

  private float[] touchSamples = new float[touch.sampleSize()];
  private float[] lightSamples = new float[ambient.sampleSize()];

  public SensorManager() {}

  public int getTouch() {
    touch.fetchSample(touchSamples, 0);
    return (int) touchSamples[0];
  }

  public float getAmbient() {
    ambient.fetchSample(lightSamples, 0);
    return lightSamples[0];
  }
}
