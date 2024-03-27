package com.coolteam;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class PenController {
  public enum PEN_STATE {
    UP,
    DOWN
  };

  BaseRegulatedMotor motor = new EV3MediumRegulatedMotor(MotorPort.A);
  private PEN_STATE state = PEN_STATE.UP;

  public PEN_STATE getPenState() {
    return state;
  }

  public void penDown() {
    motor.rotateTo(30);
    state = PEN_STATE.DOWN;
  }

  public void penUp() {
    motor.rotateTo(0);
    state = PEN_STATE.UP;
  }

  public void move(int x, int y) {}

  public PenController() {
    motor.setSpeed(30);
  }
}
