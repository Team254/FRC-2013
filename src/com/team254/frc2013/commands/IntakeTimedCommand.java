/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Richard
 * @author Arthur
 */
public class IntakeTimedCommand extends CommandBase {
  private double power = 0.0;
  private double time = 0.0;
  private Timer t = new Timer();
  
  public IntakeTimedCommand(double power,double time){
    this.time = time;
    this.power = power;
    requires(intake);
  }

  protected void initialize() {
    t.start();
  }

  protected void execute() {
    intake.setIntakePower(power);
  }

  protected boolean isFinished() {
    return t.get() >= time;
  }

  protected void end() {
    intake.setIntakePower(0.0);
  }

  protected void interrupted() {
  }
}
