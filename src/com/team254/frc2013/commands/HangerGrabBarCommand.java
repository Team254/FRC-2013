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
    System.out.println("Starting hanger extension.");
    setToEnd = false;
    drive.shift(true);
    hanger.setPto(true);
    hanger.setGoal(stagingGoal);
    hanger.enableController(true);
  }

  protected void execute() {
    System.out.println("Extending hanger...");
    if (!setToEnd && clearToTop() && (motors.getLeftEncoder().get() - stagingGoal) < 300) {
      System.out.println("Hanger clear to extend fully.");
      hanger.setGoal(endGoal);
      setToEnd = true;
    }
  }

  protected boolean isFinished() {
    return setToEnd && motors.getLeftEncoder().get() < endGoal;
  }

  protected void end() {
    System.out.println("Finished extending hanger.");
    hanger.enableController(false);
    motors.set(0);
  }

  protected void interrupted() {
    hanger.enableController(false);
    motors.set(0);
  }
}
