package com.team254.frc2013.commands;

/**
 * Waits for a certain distance to be driven.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class WaitForDistanceCommand extends CommandBase {
  private double distance;

  public WaitForDistanceCommand(double distance, double timeout) {
    this.distance = distance;
    setTimeout(timeout);
  }

  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    double averageDistance = (drive.getLeftEncoderDistance() + drive.getRightEncoderDistance()) / 2;
    return averageDistance > distance || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
