package com.team254.frc2013.commands;

/**
 * Moves indexer back to the shooter after having loaded a disc.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SensedIndexerUpCommand extends CommandBase {

  public SensedIndexerUpCommand(double timeout) {
    requires(intake);
    requires(conveyor);
    setTimeout(timeout);
  }

  public SensedIndexerUpCommand() {
    this(1.0);
  }

  protected void initialize() {
    shooter.setIndexerUp(true);
    intake.setIntakePower(0);
    conveyor.setMotor(0);
  }

  protected void execute() {
    System.out.println("looking for up: " + shooter.isIndexerSensedUp());
  }

  protected boolean isFinished() {
    return shooter.isIndexerSensedUp() || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
