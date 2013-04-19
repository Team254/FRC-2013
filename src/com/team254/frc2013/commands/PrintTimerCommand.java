package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Gets and prints the time elapsed of a timer.
 *
 * @author Richard
 */
public class PrintTimerCommand extends CommandBase {
  private Timer timer;

  public PrintTimerCommand(Timer timer) {
    this.timer = timer;
  }
  
  protected void initialize() {
    System.out.println("Last shoot sequence time: " + timer.get());
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
