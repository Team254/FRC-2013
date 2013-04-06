package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Moves indexer back to the shooter after having loaded a disc.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SetIndexerDownCommand extends CommandBase {

  public SetIndexerDownCommand() {;
    requires(intake);
    requires(conveyor);
  }

  protected void initialize() {
    shooter.setIndexerUp(false);
    intake.setIntakePower(0);
    conveyor.setMotor(0);
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
