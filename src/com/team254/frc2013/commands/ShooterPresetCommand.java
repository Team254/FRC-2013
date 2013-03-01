package com.team254.frc2013.commands;

/**
 * Sets the shooter preset speed and whether it is angled up or down.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShooterPresetCommand extends CommandBase {
  private int preset;

  public ShooterPresetCommand(int preset) {
    requires(shooter);
    this.preset = preset;
  }

  protected void initialize() {
  }

  protected void execute() {
    shooter.setPreset(preset);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
