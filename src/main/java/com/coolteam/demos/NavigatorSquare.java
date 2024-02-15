package com.coolteam.demos;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public class NavigatorSquare {
  BaseRegulatedMotor motor_left = new EV3LargeRegulatedMotor(MotorPort.A);
  BaseRegulatedMotor motor_right = new EV3LargeRegulatedMotor(MotorPort.B);
  static final float DIAMETER = 51;
  static final float AXLE = 44;

  public NavigatorSquare() {
    Wheel w_left = WheeledChassis.modelWheel(motor_left, DIAMETER).offset(-AXLE / 2);
    Wheel w_right = WheeledChassis.modelWheel(motor_right, DIAMETER).offset(AXLE / 2);
    WheeledChassis c =
        new WheeledChassis((new Wheel[] {w_right, w_left}), WheeledChassis.TYPE_DIFFERENTIAL);
    MovePilot p = new MovePilot(c);
    PoseProvider pose = new OdometryPoseProvider(p);
    Navigator n = new Navigator(p, pose);
    Path square_path = new Path();
    square_path.add(new Waypoint(100, 0));
    square_path.add(new Waypoint(100, 100));
    square_path.add(new Waypoint(0, 100));
    square_path.add(new Waypoint(0, 0));
    n.followPath(square_path);
    if (n.waitForStop()) {
      LCD.clear();
      LCD.drawString(
          String.format("X: %f, Y: %f\n", pose.getPose().getX(), pose.getPose().getY()), 0, 1);
    }
  }
}
