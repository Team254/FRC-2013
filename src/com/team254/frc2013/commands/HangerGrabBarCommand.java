package com.team254.frc2013.commands;

/**
 * Drives the hanger to its end (over the bar) position
 * while optionally stopping at a staging position (right
 * below the bar) to allow for swing.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class HangerGrabBarCommand extends CommandBase {
  private double stagingGoal, endGoal, minForeswingAngle, minBackswingAngle;
  boolean setToEnd;

  public HangerGrabBarCommand(double stagingGoal, double endGoal,
          double minForeswingAngle, double minBackswingAngle) {
    requires(drive);
    this.stagingGoal = stagingGoal;
    this.endGoal = endGoal;
    this.minForeswingAngle = minForeswingAngle;
    this.minBackswingAngle = minBackswingAngle;
  }

  private boolean clearToTop() {
    return hanger.getPitchAngle() > minForeswingAngle && hanger.getPitchRate() > 0 ||
        hanger.getPitchAngle() > minBackswingAngle;
  }

  protected void initialize() {
    setToEnd = false;
    hanger.setPto(true);
    hanger.setGoal(stagingGoal);
    hanger.enableController(true);
  }

  protected void execute() {
    if (!setToEnd && clearToTop() && (motors.getLeftEncoder().get() - stagingGoal) < 500) {
      hanger.setGoal(endGoal);
      setToEnd = true;
    }
  }

  protected boolean isFinished() {
    return setToEnd && hanger.onTarget() || Math.abs(controlBoard.leftStick.getY()) > 0.3;
  }

  protected void end() {
    hanger.enableController(false);
    motors.set(0);
  }

  protected void interrupted() {
    hanger.enableController(false);
    motors.set(0);
  }
}
