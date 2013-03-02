package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Grabs Frisbee from conveyor and loads it into shooter.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 */
public class AutoIndexCommand extends CommandBase {
  boolean conveyorOn;
  private Timer conveyorTimer;

  public AutoIndexCommand() {
    conveyorTimer = new Timer();
  }

  protected void initialize() {
    setTimeout(3);
    shooter.setIndexerUp(false);
    conveyorOn = false;
    conveyorTimer.stop();
    conveyorTimer.reset();
  }

  protected void execute() {
    if (shooter.isIndexerDown() && shooter.isShooterBack() && !conveyorOn) {
      conveyorOn = true;
      shooter.setIndexerUp(true);
      conveyor.setMotor(0.9);
      conveyorTimer.reset();
      conveyorTimer.start();
    }
  }

  protected boolean isFinished() {
    return conveyorTimer.get() > 0.5 || isTimedOut();
  }

  protected void end() {
    shooter.setIndexerUp(true);
    conveyor.setMotor(0);
  }

  protected void interrupted() {
    shooter.setIndexerUp(true);
    conveyor.setMotor(0);
  }
}
