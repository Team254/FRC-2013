package com.team254.frc2013.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains functions for use with the Logitech F310 controller.
 *
 * @author articgrayling8x8@gmail.com (Dorian Chan)
 * @author kevinsundar@gmail.com (Kevin Vincent)
 */
public class Gamepad extends Joystick {
  // Gamepad axis ports
  private static final int AXIS_LEFT_X = 1;
  private static final int AXIS_LEFT_Y = 2;
  private static final int AXIS_SHOULDER = 3;
  private static final int AXIS_RIGHT_X = 4;
  private static final int AXIS_RIGHT_Y = 5;
  private static final int AXIS_DPAD = 6;

  // Gamepad buttons
  private static final int BUTTON_A = 1; // Bottom Button
  private static final int BUTTON_B = 2; // Right Button
  private static final int BUTTON_X = 3; // Left Button
  private static final int BUTTON_Y = 4; // Top Button
  private static final int BUTTON_SHOULDER_LEFT = 5;
  private static final int BUTTON_SHOULDER_RIGHT = 6;
  private static final int BUTTON_BACK = 7;
  private static final int BUTTON_START = 8;
  private static final int BUTTON_LEFT_STICK = 9;
  private static final int BUTTON_RIGHT_STICK = 10;
  private static final int BUTTON_MODE = -1;
  private static final int BUTTON_LOGITECH = -1;
  private static final int kDPadXAxisNum = 5;
  private static final int kDPadYAxisNum = 6;

  /**
   * Constructor that creates a Joystick object.
   */
  public Gamepad(int gamepadPort) {
    super(gamepadPort);
  }

  /**
   * Returns the X position of the left stick.
   */
  public double getLeftX() {
    return getRawAxis(AXIS_LEFT_X);
  }

  /**
   * Returns the X position of the right stick.
   */
  public double getRightX() {
    return getRawAxis(AXIS_RIGHT_X);
  }

  /**
   * Returns the Y position of the left stick.
   */
  public double getLeftY() {
    return getRawAxis(AXIS_LEFT_Y);
  }

  /**
   * Returns the Y position of the right stick.
   */
  public double getRightY() {
    return getRawAxis(AXIS_RIGHT_Y);
  }

  /**
   * Checks whether Button A is being pressed and returns true if it is.
   */
  public boolean getButtonStateA() {
    return getRawButton(BUTTON_A);
  }

  /**
   * Checks whether Button B is being pressed and returns true if it is.
   */
  public boolean getButtonStateB() {
    return getRawButton(BUTTON_B);
  }

  /**
   * Checks whether Button X is being pressed and returns true if it is.
   */
  public boolean getButtonStateX() {
    return getRawButton(BUTTON_X);
  }

  /**
   * Checks whether Button Y is being pressed and returns true if it is.
   */
  public boolean getButtonStateY() {
    return getRawButton(BUTTON_Y);
  }

  /**
   * Returns an object of Button A.
   */
  public JoystickButton getButtonA() {
    return new JoystickButton(this, BUTTON_A);
  }

  /**
   * Returns an object of Button B.
   */
  public JoystickButton getButtonB() {
    return new JoystickButton(this, BUTTON_B);
  }

  /**
   * Returns an object of Button X.
   */
  public JoystickButton getButtonX() {
    return new JoystickButton(this, BUTTON_X);
  }

  /**
   * Returns an object of Button Y.
   */
  public JoystickButton getButtonY() {
    return new JoystickButton(this, BUTTON_Y);
  }

  /**
   * Return the DPad axis positions.
   */
  private double getDPadX() {
    return getRawAxis(kDPadXAxisNum);
  }

  private double getDPadY() {
    return getRawAxis(kDPadYAxisNum);
  }

  /**
   * DPad - in between.
   */
  public boolean getDPadUpLeft() {
    double x = getDPadX();
    double y = getDPadY();
    return (x < -0.5 && y < -0.5);
  }

  public boolean getDPadDownLeft() {
    double x = getDPadX();
    double y = getDPadY();
    return (x < -0.5 && y > 0.5);
  }

  public boolean getDPadDownRight() {
    double x = getDPadX();
    double y = getDPadY();
    return (x > 0.5 && y > 0.5);
  }

  public boolean getDPadUpRight() {
    double x = getDPadX();
    double y = getDPadY();
    return (x > 0.5 && y < -0.5);
  }

  /**
   * DPad - cardinal directions.
   */
  public boolean getDPadUp() {
    double y = getDPadY();
    return (y < -0.5);
  }

  public boolean getDPadLeft() {
    double x = getDPadX();
    return (x < -0.5);
  }

  public boolean getDPadDown() {
    double y = getDPadY();
    return (y > 0.5);
  }

  public boolean getDPadRight() {
    double x = getDPadX();
    return (x > 0.5);
  }
}
