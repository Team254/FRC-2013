package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Grabs Frisbee from conveyor and loads it into shooter.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 */
public class IndexerCommand extends CommandBase {
  private boolean up;
  private Timer conveyorTimer;

  public IndexerCommand(boolean up) {
    this.up = up;
    conveyorTimer = new Timer();
  }

  protected void initialize() {
    shooter.setIndexerUp(up);
    if (up) {
      conveyor.setMotor(0.9);
      conveyorTimer.reset();
      conveyorTimer.start();
    }
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return !up || conveyorTimer.get() > 0.5;
  }

  protected void end() {
    conveyor.setMotor(0);
  }

  protected void interrupted() {
  }
}
