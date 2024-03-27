package com.coolteam;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MotorManager {
  private EV3LargeRegulatedMotor x = new EV3LargeRegulatedMotor(MotorPort.A);
  private EV3MediumRegulatedMotor pen = new EV3MediumRegulatedMotor(MotorPort.B);
  private EV3LargeRegulatedMotor y = new EV3LargeRegulatedMotor(MotorPort.C);

  private static final int speed = 100;

  public MotorManager() {
    x.setSpeed(speed);
    y.setSpeed(speed);
    pen.setSpeed(15);
  }

  public void MoveIndefiniteX() {
    x.forward();
  }

  public void MoveIndefiniteY() {
    y.backward();
  }

  public void MoveX(int dist) {
    x.rotate(dist);
  }

  public void MoveY(int dist) {
    y.rotate(-dist);
  }

  public void MovePen() {
    // Move pen
  }
}
