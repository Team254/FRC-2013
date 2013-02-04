/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author Richard
 * @author Arthur
 */
public class IntakeTimedCommand extends CommandBase {
  private double power = 0.0;
  private double timeout = 0.0;
  
  public IntakeTimedCommand(double power, double timeout) {
    this.timeout = timeout;
    this.power = power;
    requires(intake);
  }

  protected void initialize() {
    setTimeout(timeout);
  }

  protected void execute() {
    intake.setIntakePower(power);
  }

  protected boolean isFinished() {
    return isTimedOut();
  }

  protected void end() {
    intake.setIntakePower(0.0);
  }

  protected void interrupted() {
  }
}
