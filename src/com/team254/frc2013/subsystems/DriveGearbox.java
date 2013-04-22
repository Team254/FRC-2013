package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Represents the six motors in the drive gearbox.
 * Used by the drive and the climbing mechanism via power take-off.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */
public class DriveGearbox extends Subsystem{
  // Speed controllers
  private Talon leftDriveA = new Talon(Constants.leftDrivePortA.getInt());
  private Talon leftDriveB = new Talon(Constants.leftDrivePortB.getInt());
  private Talon leftDriveC = new Talon(Constants.leftDrivePortC.getInt());
  private Talon rightDriveA = new Talon(Constants.rightDrivePortA.getInt());
  private Talon rightDriveBC = new Talon(Constants.rightDrivePortBC.getInt());

  // Sensors
  private Encoder leftEncoder = new Encoder(Constants.leftEncoderPortA.getInt(),
          Constants.leftEncoderPortB.getInt(), true);
  private Encoder rightEncoder = new Encoder(Constants.rightEncoderPortA.getInt(),
          Constants.rightEncoderPortB.getInt());

  private boolean isDriveMode = true;

  private boolean zeroed = false;

  public Encoder getLeftEncoder() {
    return leftEncoder;
  }

  public Encoder getRightEncoder() {
    return rightEncoder;
  }

  public void driveLR(double leftPower, double rightPower){
    if (isDriveMode) {
      leftDriveA.set(leftPower);
      leftDriveB.set(leftPower);
      leftDriveC.set(leftPower);
      rightDriveA.set(rightPower);
      rightDriveBC.set(rightPower);
    }
  }

  public void set(double allPower){
    if (!isDriveMode) {
      if (allPower  < 0 && leftEncoder.get() < -3190) // go up power, go down encoder
        allPower = 0;
      if (allPower > 0 && leftEncoder.get() > -4)
        allPower = 0;
      leftDriveA.set(allPower);
      leftDriveB.set(allPower);
      leftDriveC.set(allPower);
      rightDriveA.set(-allPower);
      rightDriveBC.set(-allPower);
    }
  }

  public void setLeftDriveA(double power) {
    leftDriveA.set(power);
  }

  public void setLeftDriveB(double power) {
    leftDriveB.set(power);
  }

  public void setLeftDriveC(double power) {
    leftDriveC.set(power);
  }

  public void setRightDriveA(double power) {
    rightDriveA.set(power);
  }

  public void setRightDriveBC(double power) {
    rightDriveBC.set(power);
  }

  public void setDriveMode(boolean wantsDrive) {
    isDriveMode = wantsDrive;
    if (!isDriveMode && !zeroed) {
      System.out.println("ZERORING LEFT ENCODER in motors");
      leftEncoder.reset();
      zeroed = true;
    }
  }

  public boolean isDriveMode() {
    return isDriveMode;
  }

  public double getLeftPower() {
    return leftDriveA.get();
  }

  public double getRightPower() {
    return rightDriveA.get();
  }


  protected void initDefaultCommand() {
  }
}
