package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Grabs Frisbee from conveyor and loads it into shooter.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class AutonIndexerCommand extends CommandBase {
  private Timer afterDownDelayTimer;
  private boolean downDelayStarted;
  
  public AutonIndexerCommand() {
    afterDownDelayTimer = new Timer();
  }

  protected void initialize() {
    setTimeout(1.5);
    shooter.setIndexerUp(false);
    afterDownDelayTimer.stop();
    afterDownDelayTimer.reset();
    downDelayStarted = false;
  }

  protected void execute() {
    if (shooter.isIndexerDown() && !downDelayStarted) {
      downDelayStarted = true;
      afterDownDelayTimer.start();
    }
  }

  protected boolean isFinished() {
    return afterDownDelayTimer.get() > 0.2 || isTimedOut();
  }

  protected void end() {
    shooter.setIndexerUp(true);
  }

  protected void interrupted() {
    shooter.setIndexerUp(true);
  }
}
