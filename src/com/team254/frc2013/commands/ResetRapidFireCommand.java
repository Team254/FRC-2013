/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 * Resets the rapid fire counter
 *
 * @author tom@team254.com
 */
public class ResetRapidFireCommand extends CommandBase {

  protected void initialize() {
    rapidFireShots = 0;
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
