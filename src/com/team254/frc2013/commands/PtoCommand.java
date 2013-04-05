package com.team254.frc2013.commands;

/**
 * Enables or disables PTO in the drive gearboxes.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class PtoCommand extends CommandBase{
  public PtoCommand() {
    requires(drive);
  }

  protected void initialize() {
  }

  protected void execute() {
    drive.shift(true);
    hanger.setPto(true);
    motors.set(controlBoard.leftStick.getY());
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    hanger.setPto(false);
  }

  protected void interrupted() {
    hanger.setPto(false);
  }
}
