package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Represents the six motors in the drive gearbox.
 * Used by the drive and the climbing mechanism via power take-off.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */
public class DriveMotors extends Subsystem{
  private Talon leftDriveA = new Talon(Constants.leftDrivePortA.getInt());
  private Talon leftDriveB = new Talon(Constants.leftDrivePortB.getInt());
  private Talon leftDriveC = new Talon(Constants.leftDrivePortC.getInt());
  private Talon rightDriveA = new Talon(Constants.rightDrivePortA.getInt());
  private Talon rightDriveBC = new Talon(Constants.rightDrivePortBC.getInt());
  
  private boolean isDriveMode = true;
  
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
      leftDriveA.set(allPower);
      leftDriveB.set(allPower);
      leftDriveC.set(allPower);
      rightDriveA.set(-allPower);
      rightDriveBC.set(-allPower);
    }
  }
  
  public void setMotor(int portNum, double power) {
    if(portNum == 3) {
      leftDriveA.set(power);
    }
    else if(portNum == 4) {
      leftDriveB.set(power);
    }
    else if(portNum == 5) {
      leftDriveC.set(power);
    }
    else if(portNum == 6) {
      rightDriveA.set(power);
    }
    else if(portNum == 7) {
      rightDriveBC.set(power);
    }
  }
  
  public void setDriveMode(boolean wantsDrive) {
    isDriveMode = wantsDrive;
  }
  
  public boolean isDriveMode() {
    return isDriveMode;
  }
  
  protected void initDefaultCommand() {
  }
}
