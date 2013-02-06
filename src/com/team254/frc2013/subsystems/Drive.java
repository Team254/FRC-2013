package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
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
  private Talon leftDriveC = new Talon(Constants.leftDrivePortC.getInt());
  private Talon rightDriveA = new Talon(Constants.rightDrivePortA.getInt());
  private Talon rightDriveB = new Talon(Constants.rightDrivePortB.getInt());
  private Talon rightDriveC = new Talon(Constants.rightDrivePortC.getInt());

  // Sensors
  private Encoder leftEncoder = new Encoder(Constants.leftEncoderPortA.getInt(),
          Constants.leftEncoderPortB.getInt());
  private Encoder rightEncoder = new Encoder(Constants.rightEncoderPortA.getInt(),
          Constants.rightEncoderPortB.getInt());
  
  // Shifter
  private Solenoid shifter = new Solenoid(Constants.shifterPort.getInt());
  private Gyro gyro = new Gyro(Constants.gyroPort.getInt());
  private double maxSpeed = 1.0;
  private boolean isHighGear = true;

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
    leftDriveC.set(leftPower);
    rightDriveA.set(-rightPower);
    rightDriveB.set(-rightPower);
    rightDriveC.set(-rightPower);
  }
  
  public void setMotor(int portNum, double power) {
    if(portNum == 3)
      rightDriveA.set(power);
    else if(portNum == 4)
      rightDriveB.set(power);
    else if(portNum == 5)
      rightDriveC.set(power);
    else if(portNum == 6)
      leftDriveA.set(power);
    else if(portNum == 7)
      leftDriveB.set(power);
    else if(portNum == 8)
      leftDriveC.set(power);
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
  
  public double getGyroAngle() {
    return gyro.getAngle();
  }
  
  public void resetGyro() {
    gyro.reset();
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
