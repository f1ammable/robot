package lejos.hardware;

public interface Sounds {
  // Instruments (yes I know they don't sound anything like the names!)
  public static final int[] PIANO = new int[] {4, 25, 500, 7000, 5};
  public static final int[] FLUTE = new int[] {10, 25, 2000, 1000, 25};
  public static final int[] XYLOPHONE = new int[] {1, 8, 3000, 5000, 5};

  public static final int BEEP = 0;
  public static final int DOUBLE_BEEP = 1;
  public static final int ASCENDING = 2;
  public static final int DESCENDING = 3;
  public static final int BUZZ = 4;

  public static final int VOL_MAX = 100;
}
