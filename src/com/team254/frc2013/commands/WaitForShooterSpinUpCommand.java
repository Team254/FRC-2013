/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author tombot
 */
public class WaitForShooterSpinUpCommand extends CommandBase  {

  public WaitForShooterSpinUpCommand(double timeout) {
    setTimeout(timeout);
  }
  protected void initialize() {
  }

  protected void execute() {

  }

  protected boolean isFinished() {
    return shooter.onSpeedTarget() || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }


}
