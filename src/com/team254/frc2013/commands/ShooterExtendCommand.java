package com.team254.frc2013.commands;

/**
 * Retracts the shooter.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShooterExtendCommand extends CommandBase {
  public ShooterExtendCommand() {
  }

  protected void initialize() {
    shooter.extend();
    System.out.println("Shot timer: " + shotTimer.get());
    shotTimer.reset();
    shotTimer.start();
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
