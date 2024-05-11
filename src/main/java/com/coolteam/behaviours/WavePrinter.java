package com.coolteam.behaviours;

import com.coolteam.MotorManager;
import com.coolteam.SensorManager;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class WavePrinter implements Behavior {
  private boolean clapDetected = false;
  private boolean suppressed = false;
  private final float clapThreshold = SensorManager.getVolume() * 0.5f;
  private final long clapInterval = 500; // Increased clap interval to 500ms
  private final int maxXPosition = 500; // Maximum X-axis position
  private int currentXPosition = 0;
  int clapCount = 0;

  public WavePrinter() {}

  //  @Override
  //  public boolean takeControl() {
  //    long end = System.currentTimeMillis() + 5000;
  //    int clapCount = 0;
  //    long lastClapTime = 0;
  //
  //    while (System.currentTimeMillis() < end) {
  //      float soundValue = SensorManager.getVolume();
  //

  //
  //      try {
  //        LCD.drawString(String.valueOf(clapCount), 0, 3);
  //        Thread.sleep(100);
  //      } catch (InterruptedException e) {
  //        throw new RuntimeException(e);
  //      }
  //
  //      if (clapCount >= 3) { // Reduced clap count threshold to 3
  //        clapDetected = true;
  //        return true;
  //      }
  //
  //      Delay.msDelay(50);
  //    }
  //
  //    return false;
  //  }

  @Override
  public boolean takeControl() {
    LCD.clear();
    long startTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - startTime < 5000) {
      float soundValue = SensorManager.getVolume();

      try {
        LCD.drawString("Clap your hands", 0, 3);
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      if (soundValue > clapThreshold) {
        clapCount++;
        while (SensorManager.getVolume() > clapThreshold) {
          // Wait until the sound level drops below the threshold
          Delay.msDelay(50);
        }
      }

      if (clapCount >= 3) {
        clapDetected = true;
        return true;
      }

      Delay.msDelay(50);
    }

    return false;
  }

  @Override
  public void action() {
    suppressed = false;
    MotorManager.MovePen();
    MotorManager.MoveInverseY();

    // Initialize variables for smoothing
    int smoothingWindow = 5; // Adjust the window size as needed
    float[] soundBuffer = new float[smoothingWindow];
    int bufferIndex = 0;

    while (!suppressed) {
      float soundValue = SensorManager.getVolume();

      // Add the current sound value to the buffer
      soundBuffer[bufferIndex] = soundValue;
      bufferIndex = (bufferIndex + 1) % smoothingWindow;

      // Calculate the average sound value from the buffer
      float averageSoundValue = 0;
      for (float value : soundBuffer) {
        averageSoundValue += value;
      }
      averageSoundValue /= smoothingWindow;

      if (averageSoundValue > clapThreshold) {
        int xMovement =
            (int)
                (averageSoundValue * 100); // Increase the multiplier for more exaggerated movement
        if (currentXPosition + xMovement <= maxXPosition) {
          MotorManager.MoveX(xMovement);
          currentXPosition += xMovement;
        }

        int yMovement = (int) (averageSoundValue * 200);
        MotorManager.MoveY(yMovement);
      } else {
        MotorManager.MoveX(currentXPosition);
        currentXPosition = 0;
      }

      Delay.msDelay(100);
    }

    MotorManager.StopX();
    MotorManager.StopY();
  }

  @Override
  public void suppress() {
    suppressed = true; // Set the suppressed flag to true when suppressed
  }
}
