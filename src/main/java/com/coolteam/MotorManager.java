package com.coolteam;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorManager {
  private static EV3LargeRegulatedMotor x = new EV3LargeRegulatedMotor(MotorPort.A);
  private static EV3MediumRegulatedMotor pen = new EV3MediumRegulatedMotor(MotorPort.C);
  private static EV3LargeRegulatedMotor y = new EV3LargeRegulatedMotor(MotorPort.B);

  private static final int speed = 100;

  public static void init() {
    x.setSpeed(speed);
    y.setSpeed(speed);
    pen.setSpeed(100);
  }

  public static void MoveIndefiniteX() {
    x.forward();
  }

  public static void MoveIndefiniteY() {
    y.backward();
  }

  public static void MoveX(int dist) {
    x.rotate(dist);
  }

  public static void MoveY(int dist) {
    y.rotate(-dist);
  }

  public static void MovePen() {
    pen.rotate(180);
  }
}
