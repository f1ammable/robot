package lejos.hardware.port;

/**
 * An abstraction for a motor port that supports RCX type motors, but not NXT motors with
 * tachometers.
 *
 * @author Lawrie Griffiths.
 */
public interface BasicMotorPort extends IOPort {
  /** PWM Mode. Motor is not driven during off phase of PWM */
  public static final int PWM_FLOAT = 0;

  /** PWM Mode. Motor is driven during off phase of PWM */
  public static final int PWM_BRAKE = 1;

  /** Motor is running forward */
  public static final int FORWARD = 1;

  /** Motor is running backwards */
  public static final int BACKWARD = 2;

  /** Motor is stopped (PWM drive still applied) */
  public static final int STOP = 3;

  /** Motor is floating (no PWM drive) */
  public static final int FLOAT = 4;

  /** Maximum power setting = 100% */
  public static final int MAX_POWER = 100;

  public void controlMotor(int power, int mode);

  public void setPWMMode(int mode);
}
