package com.coolteam;

import java.util.ArrayList;

public class MorsePress {
  private final ArrayList<MorseType> samples = new ArrayList<>();

  public MorsePress() {}

  public void AddPress(MorseType t) {
    switch (t.ordinal()) {
      case 0:
        samples.add(MorseType.SHORT);
        break;
      case 1:
        samples.add(MorseType.LONG);
        break;
    }
  }

  public ArrayList<Integer> toIntArray() {
    ArrayList<Integer> processed = new ArrayList<>();
    for (MorseType sample : samples) {
      processed.add(sample.ordinal());
    }
    return processed;
  }
}
