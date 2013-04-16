package com.team254.frc2013.commands;

/**
 * Moves indexer back to the shooter after having loaded a disc.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SensedIndexerDownCommand extends CommandBase {

  public SensedIndexerDownCommand(double timeout) {
    requires(intake);
    requires(conveyor);
    setTimeout(timeout);
  }

  public SensedIndexerDownCommand() {
    this(1.0);
  }

  protected void initialize() {
    shooter.setIndexerUp(false);
    intake.setIntakePower(0);
    conveyor.setMotor(0);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return shooter.isIndexerSensedDown() || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
