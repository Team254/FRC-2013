package com.team254.frc2013.commands;

/**
 * Grabs frisbee from conveyor and loads it into shooter.
 * 
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 */
public class IndexerCommand extends CommandBase {
  
  public IndexerCommand() {
    //requires(indexer);
  }
  
  protected void initialize() {
  }

  protected void execute() {
   // indexer.setPistonDown(false);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
   // indexer.setPistonDown(true);
  }

  protected void interrupted() {
  }
  
}
