package com.team254.frc2013.commands;

/**
 * Grabs Frisbee from conveyor and loads it into shooter.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class AutonIndexerCommand extends CommandBase {
  public AutonIndexerCommand() {
  }

  protected void initialize() {
    setTimeout(3);
    shooter.setIndexerUp(false);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return shooter.isIndexerDown() || isTimedOut();
  }

  protected void end() {
    shooter.setIndexerUp(true);
  }

  protected void interrupted() {
    shooter.setIndexerUp(true);
  }
}
