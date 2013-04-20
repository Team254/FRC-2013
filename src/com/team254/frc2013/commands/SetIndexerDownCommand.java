package com.team254.frc2013.commands;

/**
 * Moves indexer back to the shooter after having loaded a disc.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SetIndexerDownCommand extends CommandBase {
  private boolean wantsDown;

  public SetIndexerDownCommand() {
    this(true);
  }

  public SetIndexerDownCommand(boolean wantsDown) {
    this.wantsDown = wantsDown;
  }

  protected void initialize() {
    shooter.setIndexerUp(!wantsDown);
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
