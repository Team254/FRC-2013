/*
 * ShiftCommand(highGear)
 * Shifts the drive gearbox high or low
 */
package com.team254.frc2013.commands;

/**
 *
 * @author Tom Bottiglieri
 */
public class ShiftCommand  extends CommandBase {
  boolean highGear = false;

  public ShiftCommand(boolean highGear) {
    this.highGear = highGear;
  }

  protected void initialize() {
    requires(drive);
  }

  protected void execute() {
    drive.shift(highGear);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
