package com.team254.frc2013.commands;

/**
 * Shifts the drive gearbox into high or low gear.
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ShiftCommand extends CommandBase {
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
