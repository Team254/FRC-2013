/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author tombot
 */
public class StopDriveCommand extends CommandBase {

  protected void initialize() {
    drive.setLeftRightPower(0, 0);
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
