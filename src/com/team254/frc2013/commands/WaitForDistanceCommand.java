/*
 * Waits for a certain distance to be driven.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author Tom Bottiglieri (tom@team254.com)
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
    return ((drive.getLeftEncoderDistance() + drive.getRightEncoderDistance()) / 2) > distance  || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
