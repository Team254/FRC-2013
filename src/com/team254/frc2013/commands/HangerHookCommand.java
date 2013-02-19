/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author Richard
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
