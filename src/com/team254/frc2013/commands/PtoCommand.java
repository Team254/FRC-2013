package com.team254.frc2013.commands;

/**
 * Enables or disables PTO in the drive gearboxes.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class PtoCommand extends CommandBase {

  boolean reset = false;
  public PtoCommand(boolean reset) {
    requires(drive);
    requires(motors);
    this.reset = reset;
  }

  public PtoCommand() {
    this(false);
  }

  protected void initialize() {
    if (reset)
      motors.getLeftEncoder().reset();
  }

  protected void execute() {
    drive.shift(true);
    hanger.setPto(true);
    motors.set(controlBoard.gamePad.getY());
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    hanger.setPto(false);
  }

  protected void interrupted() {
    hanger.setPto(false);
  }
}
