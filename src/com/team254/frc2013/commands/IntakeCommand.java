package com.team254.frc2013.commands;

/**
 * Sends power to the intake based on gamepad control.
 * The gamepad control scheme is temporary.
 * 
 * @author art.kalb96@gmail.com (Arthur Kalb)
 */
public class IntakeCommand extends CommandBase {
  private double value = 0.0;
  private double increment = 0.005;
  
  public IntakeCommand() {
    requires(intake);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    if(controlBoard.gamepad.getStartButton()){
      value = 0.0;
    } else if(controlBoard.gamepad.getDPadLeft()){
      value = -1.0;
    } else if(controlBoard.gamepad.getDPadRight()){
      value = 1.0;
    } else if(controlBoard.gamepad.getRightShoulder()){
      value += increment;
    } else if(controlBoard.gamepad.getLeftShoulder()){
      value -= increment;
    }
    value = Math.abs(value) > 1.0 ? Math.abs(value) / value : value;
    //System.out.println("Intake Value: " + value);
    //intake.setIntakePower(value);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
