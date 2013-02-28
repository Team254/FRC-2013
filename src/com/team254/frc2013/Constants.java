package com.team254.frc2013;

import com.team254.lib.util.ConstantsBase;

/**
 * Manages constant values used everywhere in the robot code.
 * Variables can be declared here with default values and overridden with a text file uploaded to
 * the robot's file system.
 *
 * @author brandon.gonzalez.451@gmail.com (Brandon Gonzalez)
 */
public class Constants extends ConstantsBase {
  // Declare the constants and their default values here.
  // Control board mappings
  public static final Constant leftJoystickPort = new Constant("leftJoystickPort", 1);
  public static final Constant rightJoystickPort = new Constant("rightJoystickPort", 2);
  public static final Constant gamepadPort = new Constant("gamepadPort", 3);

  // Speed controller mappings
  public static final Constant intakePivotPort = new Constant("intakePivotPort", 1);
  public static final Constant intakePort = new Constant("intakePort", 2);

  public static final Constant leftDrivePortA = new Constant("leftDrivePortA", 3);
  public static final Constant leftDrivePortB = new Constant("leftDrivePortB", 4);
  public static final Constant leftDrivePortC = new Constant("leftDrivePortC", 5);
  public static final Constant rightDrivePortA = new Constant("rightDrivePortA", 6);
  public static final Constant rightDrivePortBC = new Constant("rightDrivePortBC", 7);

  public static final Constant conveyorPort = new Constant("conveyorPort", 8);
  public static final Constant frontShooterPort = new Constant("frontShooterPort", 9);
  public static final Constant backShooterPort = new Constant("backShooterPort", 10);

  // Solenoids
  public static final Constant pressureSwitch = new Constant("pressureSwitch",  9);
  public static final Constant compressorRelay = new Constant("compressorRelay", 8);

  public static final Constant shifterPort = new Constant("shifterPort", 8);
  public static final Constant ptoPort = new Constant("ptoPort", 7);
  public static final Constant shooterAnglePort = new Constant("shooterAnglePort", 6);
  public static final Constant shooterLoaderPort = new Constant("shooterLoaderPort", 5);
  public static final Constant hangerRetractedPort = new Constant("hangerRetractedPort", 4);
  public static final Constant hangerExtendedPort = new Constant("hangerExtendedPort", 3);
  public static final Constant indexerPort = new Constant("indexerPort", 2);
  public static final Constant conveyorSolenoidPort = new Constant("conveyorSolenoidPort", 1);

  // Operator control mappings
  public static final Constant autonSelectControlPort = new Constant("autonSelectControlPort",11);
  public static final Constant conveyOutControlPort = new Constant("conveyOutControlPort", 10);
  public static final Constant conveyorControlPort = new Constant("conveyorControlPort", 9);
  public static final Constant intakeOutControlPort = new Constant("intakeOutControlPort", 8);
  public static final Constant intakeControlPort = new Constant("intakeControlPort", 7);
  public static final Constant hang30ControlPort = new Constant("hang30ControlPort", 6);
  public static final Constant hang10ControlPort = new Constant("hang10ControlPort", 5);
  public static final Constant farControlPort = new Constant("farControlPort", 4);
  public static final Constant middleControlPort = new Constant("middleControlPort", 3);
  public static final Constant closeControlPort = new Constant("closeControlPort", 2);
  public static final Constant shootControlPort = new Constant("shootControlPort", 1);
  public static final Constant shooterOnPort = new Constant("shooterOnPort", 3);
  public static final Constant intakeUpPort = new Constant("intakeUpPort", 4);
  public static final Constant intakeDownPort = new Constant("intakeDownPort", 12);

  // Sensor mappings
  public static final Constant gyroPort = new Constant("gyroPort", 1);
  public static final Constant discSensorPort = new Constant("discSensorPort", 2);
  public static final Constant pressureTransducerPort = new Constant("pressureTransducerPort", 3);

  // Drive encoders
  public static final Constant leftEncoderPortA = new Constant("leftEncoderPortA", 1);
  public static final Constant leftEncoderPortB = new Constant("leftEncoderPortB", 2);
  public static final Constant rightEncoderPortA = new Constant("rightEncoderPortA", 3);
  public static final Constant rightEncoderPortB = new Constant("rightEncoderPortB", 4);

  // Shooter encoders
  public static final Constant frontEncoderPort = new Constant("frontEncoderPort", 5);
  public static final Constant backEncoderPort = new Constant("backEncoderPort", 6);

  // Intake encoder
  public static final Constant intakeEncoderPortA = new Constant("intakeEncoderPortA", 7);
  public static final Constant intakeEncoderPortB = new Constant("intakeEncoderPortB", 8);

  // Drive tuning
  public static final Constant sensitivityHigh = new Constant("sensitivityHigh", .85);
  public static final Constant sensitivityLow = new Constant("sensitivityLow", .7);

  public static final Constant driveStraightKP = new Constant("driveStraightKP", 0.014);
  public static final Constant driveStraightKI = new Constant("driveStraightKI", .0005);
  public static final Constant driveStraightKD = new Constant("driveStraightKD", 0.02);

  public static final Constant driveTurnKP = new Constant("driveTurnKP", 0.02);
  public static final Constant driveTurnKI = new Constant("driveTurnKI", 0.0005);
  public static final Constant driveTurnKD = new Constant("driveTurnKD", 0.025);

  public static final Constant hangerKP = new Constant("hangerKP", 0.0);
  public static final Constant hangerKI = new Constant("hangerKI", 0.0);
  public static final Constant hangerKD = new Constant("hangerKD", 0.0);

  public static final Constant intakeKP = new Constant("intakeKP", 0.09);
  public static final Constant intakeKI = new Constant("intakeKI", 0.0);
  public static final Constant intakeKD = new Constant("intakeKD", 0.24);

  static {
    // Set any overridden constants from the file on startup.
    readConstantsFromFile();
  }

  // Prevent instantiation of this class, as it should only be used statically.
  private Constants() {
  }
}
