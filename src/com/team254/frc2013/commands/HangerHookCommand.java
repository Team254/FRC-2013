package com.team254.frc2013.commands;

/**
 * Controls the first-stage hanger hook
 * 
 * @author richard@team254.com (Richard Lin)
 */
public class HangerHookCommand extends CommandBase {

  boolean up = true;
  
  public HangerHookCommand(boolean up) {
    System.out.println("New hanger hook command");
    this.up = up;
    requires(hanger);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    hanger.setHookUp(up);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
