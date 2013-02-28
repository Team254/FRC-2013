package com.team254.frc2013.commands;

/**
 * Sets the shooter preset speed and whether it is angled up or down.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShooterPresetCommand extends CommandBase {
  private double speed;
  private boolean isUp;

  public ShooterPresetCommand(double speed, boolean isUp) {
    requires(shooter);
    this.speed = speed;
    this.isUp = isUp;
  }

  // Constructor that only takes in and changes speed.
  public ShooterPresetCommand(double speed) {
    requires(shooter);
    this.speed = speed;
    this.isUp = shooter.isHighAngle();
  }

  protected void initialize() {
  }

  protected void execute() {
    shooter.setHighAngle(isUp);
    shooter.setSpeeds(speed, speed);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
