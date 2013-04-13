package com.team254.frc2013.commands;

/**
 * Prints a string to the console.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class PrintCommand extends CommandBase {
  private String message;

  public PrintCommand(String message){
    this.message = message;
  }

  protected void initialize() {
  }

  protected void execute() {
    System.out.println(message);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
