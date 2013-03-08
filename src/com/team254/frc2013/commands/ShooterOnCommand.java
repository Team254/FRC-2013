package com.team254.frc2013.commands;

/**
 * Turns the shooter on or off.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShooterOnCommand extends CommandBase {
  private boolean isOn;

  public ShooterOnCommand(boolean isOn) {
    requires(shooter);
    this.isOn = isOn;
  }

  protected void initialize() {
  }

  protected void execute() {
    shooter.setShooterOn(isOn);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
