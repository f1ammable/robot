package com.coolteam;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorManager {
  private static final EV3LargeRegulatedMotor x = new EV3LargeRegulatedMotor(MotorPort.A);
  private static final EV3MediumRegulatedMotor pen = new EV3MediumRegulatedMotor(MotorPort.C);
  private static final EV3LargeRegulatedMotor y = new EV3LargeRegulatedMotor(MotorPort.B);

  private static final int speed = 50;
  private static PenState penState = PenState.DOWN;

  public static void init() {
    x.setSpeed(speed);
    y.setSpeed(speed * 2);
    pen.setSpeed(speed);
  }

  private static void SwitchPenState() {
    penState = penState == PenState.DOWN ? PenState.UP : PenState.DOWN;
  }

  public static void MoveIndefiniteX() {
    x.forward();
  }

  public static void MoveIndefiniteY() {
    y.forward();
  }

  public static void MoveInverseY() {
    y.backward();
  }

  public static void MoveX(int dist) {
    x.rotate(dist);
  }

  public static void MoveY(int dist) {
    y.rotate(-dist);
  }

  // correctPenState - only to be used during setup to sync internal pen state and physical state
  public static void MovePen(boolean correctPenState) {
    pen.rotate(180);

    if (correctPenState) SwitchPenState();
  }

  public static void StopX() {
    x.stop();
  }

  public static void StopY() {
    y.stop();
  }
}
