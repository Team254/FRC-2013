package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Moves indexer back to the shooter after having loaded a disc.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class IndexerUpCommand extends CommandBase {
  private Timer conveyorDelayTimer;

  public IndexerUpCommand() {
    conveyorDelayTimer = new Timer();
  }

  protected void initialize() {
    shooter.setIndexerUp(true);
    intake.setIntakePower(0);
    conveyor.setMotor(0);
    conveyorDelayTimer.reset();
    conveyorDelayTimer.start();
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return conveyorDelayTimer.get() > 0.1;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
