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
    intake.setIntakePower(0);
    conveyor.setMotor(0);
  }

  protected void execute() {
    if (shooter.isIndexerDown() && !downDelayStarted) {
      downDelayStarted = true;
      afterDownDelayTimer.start();
    }
    if (afterDownDelayTimer.get() > 0.4) {
      shooter.setIndexerUp(true);
    }
  }

  protected boolean isFinished() {
    return afterDownDelayTimer.get() > 0.5 || isTimedOut();
  }

  protected void end() {
    shooter.setIndexerUp(true);
    intake.setIntakePower(0.5);
    conveyor.setMotor(0.5);            
  }

  protected void interrupted() {
    shooter.setIndexerUp(true);
  }
}
