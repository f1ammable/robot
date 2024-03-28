package com.coolteam;

public enum PenState {
  DOWN,
  UP;

  private static PenState[] values = values();

  public PenState next() {
    return values[(this.ordinal() + 1) % values.length];
  }

  public PenState prev() {
    return values[(this.ordinal() - 1 + values.length) % values.length];
  }
}
