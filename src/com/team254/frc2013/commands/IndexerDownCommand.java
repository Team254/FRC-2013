package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Moves the indexer down to the conveyor to grab a disc.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class IndexerDownCommand extends CommandBase {
  private Timer afterDownDelayTimer;
  private boolean downDelayStarted;

  public IndexerDownCommand() {
    afterDownDelayTimer = new Timer();
  }

  protected void initialize() {
    setTimeout(1.5);
    shooter.setIndexerUp(false);
    afterDownDelayTimer.stop();
    afterDownDelayTimer.reset();
    downDelayStarted = false;
    intake.setIntakePower(0);
    conveyor.setMotor(0);
  }

  protected void execute() {
    if (shooter.isIndexerDown() && !downDelayStarted) {
      downDelayStarted = true;
      afterDownDelayTimer.start();
    }
  }

  protected boolean isFinished() {
    return afterDownDelayTimer.get() > 0.15 || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
