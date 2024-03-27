package com.coolteam;

import com.coolteam.behaviours.Center;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
  public static void main(String[] args) {
    Arbitrator a = new Arbitrator(new Behavior[] {new Center()});
    a.go();
  }
}
