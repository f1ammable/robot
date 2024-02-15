package com.coolteam.demos;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.*;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.ShortestPathFinder;

public class FollowThePath {
  LineMap map;
  PoseProvider p;
  MovePilot pilot;
  Chassis c;
  static final float DIAMETER = 51;
  static final float AXLE = 44;

  BaseRegulatedMotor motor_left = new EV3LargeRegulatedMotor(MotorPort.A);
  BaseRegulatedMotor motor_right = new EV3LargeRegulatedMotor(MotorPort.B);

  Wheel w_left, w_right;
  Navigator n;

  public FollowThePath() {
    LCD.clear();
    w_left = WheeledChassis.modelWheel(motor_left, DIAMETER).offset(-AXLE / 2);
    w_right = WheeledChassis.modelWheel(motor_right, DIAMETER).offset(AXLE / 2);
    c = new WheeledChassis((new Wheel[] {w_right, w_left}), WheeledChassis.TYPE_DIFFERENTIAL);
    pilot = new MovePilot(c);
    p = new OdometryPoseProvider(pilot);
    p.setPose(new Pose(0, 0, 90));
    n = new Navigator(pilot, p);
    map = new LineMap(new Line[] {new Line(200, 100, 200, 50)}, new Rectangle(0, 0, 200, 200));

    ShortestPathFinder s = new ShortestPathFinder(map);
    Path path_to_follow = null;
    try {
      path_to_follow = s.findRoute(p.getPose(), new Waypoint(200, 200), map);
    } catch (DestinationUnreachableException e) {
      LCD.drawString("Unreachable", 0, 0);
    }

    while (Button.ENTER.isUp()) {
      if (path_to_follow != null) {
        n.followPath(path_to_follow);
      } else {
        LCD.drawString("Path cannot be reached", 0, 0);
      }

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
