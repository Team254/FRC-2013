package com.team254.frc2013.commands;

/**
 * Enables or disables PTO in the drive gearboxes.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class SetPtoCommand extends CommandBase{
  private boolean on;
  
  public SetPtoCommand(boolean on) {
    this.on = on;
    requires(hanger);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    hanger.setPto(on);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  } 
}
