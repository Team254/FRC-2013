package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Checks the pitch of the robot and waits for it to be swinging,
 *  indicating that the robot is off the ground.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class WaitForLiftCommand extends CommandBase {
  Timer t = new Timer();
  boolean up = true;
  public WaitForLiftCommand() {
  }
  protected void initialize() {
    t.reset();
    t.start();
    drive.shift(true);
  }

  protected void execute() {
    System.out.println("Waiting for lift...");
    if (t.get() > .5) {
      up = !up;
    }
    sc.wantIntakeUp = up;
    sc.wantIntakeDown = !up;
  }

  protected boolean isFinished() {
    return Math.abs(hanger.getPitchAngle()) > 2;
  }

  protected void end() {
    System.out.println("Off the ground; okay for hanger extension.");
        sc.wantIntakeUp = false;
    sc.wantIntakeDown = false;
  }

  protected void interrupted() {
  }
}
