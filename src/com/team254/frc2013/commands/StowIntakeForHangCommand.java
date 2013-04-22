/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author tombot
 */
class StowIntakeForHangCommand extends CommandBase {

  public StowIntakeForHangCommand() {
  }

  protected void initialize() {
    sc.wantHang = true;
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
