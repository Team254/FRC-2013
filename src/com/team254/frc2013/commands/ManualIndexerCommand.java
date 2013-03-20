package com.team254.frc2013.commands;

/**
 * Manually set the position of the indexer.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ManualIndexerCommand extends CommandBase {
  private boolean isUp;
  
  public ManualIndexerCommand(boolean isUp) {
    this.isUp = isUp;
  }

  protected void initialize() {
  }

  protected void execute() {
    shooter.setIndexerUp(isUp);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
