package com.team254.frc2013;

import java.util.Hashtable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static final int leftMotor = 1;
  // public static final int rightMotor = 2;
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static final int rangefinderPort = 1;
  // public static final int rangefinderModule = 1;
  
  // OI hardware
  public static final int leftJoystickPort = 1;
  public static final int rightJoystickPort = 2;
  public static final int gamepadPort = 3;
  
  // Robot hardware
  public static final int leftDrivePortA = 5;
  public static final int leftDrivePortB = 4;
  public static final int rightDrivePortA = 6;
  public static final int rightDrivePortB = 7;
  
  // Intake rollers (for testing purposes)
  public static final int intakePortA = 2;
  public static final int intakePortB = 1;
  public static final int intakePortC = 10;
  
  // Sensors
  public static final int leftEncoderPortA = 2;
  public static final int leftEncoderPortB = 3;
  public static final int rightEncoderPortA = 4;
  public static final int rightEncoderPortB = 5;
  
  //Constants
  public static double testConstant = 2.54;
  
  public static void set(Hashtable constants) {
    /**
     * For every constant that could be modified at some point
     * you need to do the following:
     * 1) create its static variable on top under constants
     * 2) make an if statement searching the Hashtable for
     *    a key with the same name as the variable name
     * 3) in the if statement set the variable to the
     *    value given by the Hashtable with its corresponding
     *    key
     * 4) Edit Constants.txt for modifications
     */
      if(constants.containsKey("testConstant")) {
          Double dub = (Double)constants.get("testConstant");
          testConstant = dub.doubleValue();
      }
  }
}