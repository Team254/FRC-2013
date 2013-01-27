package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.frc2013.lib.ThrottledPrinter;
import com.team254.frc2013.lib.Util;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class representing the drivetrain and managing its motors and sensors.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Drive extends Subsystem {
  // PWM channels
  private Talon leftDriveA = new Talon(Constants.leftDrivePortA.getInt());
  private Talon leftDriveB = new Talon(Constants.leftDrivePortB.getInt());
  private Talon rightDriveA = new Talon(Constants.rightDrivePortA.getInt());
  private Talon rightDriveB = new Talon(Constants.rightDrivePortB.getInt());

  // Sensors
  private Encoder leftEncoder = new Encoder(Constants.leftEncoderPortA.getInt(),
          Constants.leftEncoderPortB.getInt());
  private Encoder rightEncoder = new Encoder(Constants.rightEncoderPortA.getInt(),
          Constants.rightEncoderPortB.getInt());
  
  // Shifter
  Solenoid shifter = new Solenoid(Constants.shifterPort.getInt());

  private double maxSpeed = 1.0;
  private boolean isHighGear = true;

  private ThrottledPrinter printer = new ThrottledPrinter(0.1);

  public Drive() {
    super();
    leftEncoder.start();
    rightEncoder.start();
  }

  protected void initDefaultCommand() {
    //setDefaultCommand(new TankDriveCommand());
    setDefaultCommand(new CheesyDriveCommand());
  }

  public void setLeftRightPower(double leftPower, double rightPower) {
    leftPower = Util.limit(leftPower, maxSpeed);
    rightPower = Util.limit(rightPower, maxSpeed);
    leftDriveA.set(leftPower);
    leftDriveB.set(leftPower);
    rightDriveA.set(-rightPower);
    rightDriveB.set(-rightPower);
  }

  public double getLeftEncoderDistance() {
    return -leftEncoder.get() / 256.0 * 3.5 * Math.PI;
  }

  public double getRightEncoderDistance() {
    return -rightEncoder.get() / 256.0 * 3.5 * Math.PI;
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void setMaxSpeed(double speed) {
    maxSpeed = speed;
  }
  
  public void shift(boolean highGear) {
    isHighGear = highGear;
    shifter.set(isHighGear);
  }
  
  public boolean isHighGear() {
    return isHighGear;
  }
}
