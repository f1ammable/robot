package com.coolteam.demos;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public class Square {

  static final float DIAMETER = 51;
  static final float AXLE = 44;
  static final float ANGULAR_SPD = 100;
  static final float LINEAR_SPD = 70;

  BaseRegulatedMotor motor_left = new EV3LargeRegulatedMotor(MotorPort.A);
  BaseRegulatedMotor motor_right = new EV3LargeRegulatedMotor(MotorPort.B);

  Wheel w_left, w_right;
  Chassis c;
  MovePilot p;
  PoseProvider pose;

  public Square() {
    w_left = WheeledChassis.modelWheel(motor_left, DIAMETER).offset(-AXLE / 2);
    w_right = WheeledChassis.modelWheel(motor_right, DIAMETER).offset(AXLE / 2);
    c = new WheeledChassis((new Wheel[] {w_right, w_left}), WheeledChassis.TYPE_DIFFERENTIAL);
    p = new MovePilot(c);
    pose = new OdometryPoseProvider(p);
  }

  // Square Pilot
  public void square() {
    p.setLinearSpeed(150);
    // Linear speed is lower than angular, so we compensate for that
    // TODO: Check if this shit is actually accurate and does what I think it does
    p.setAngularSpeed(175);

    for (int i = 0; i < 4; i++) {
      p.travel(15);
      p.rotate(90, false);
    }
    p.rotate(90, false);
  }

  // Square Navigator
  public void navigator() {
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
    ;
  }
}
