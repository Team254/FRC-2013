package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Grabs Frisbee from conveyor and loads it into shooter.
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class IndexerCommand extends CommandBase {
  private boolean up;
  private boolean controlLoopsOn;
  private boolean conveyorOn;
  private Timer conveyorTimer;

  public IndexerCommand(boolean up) {
    this.up = up;
    conveyorTimer = new Timer();
  }

  protected void initialize() {
    conveyorOn = false;
    conveyorTimer.stop();
    conveyorTimer.reset();
    controlLoopsOn = controlBoard.operatorJoystick.getControlLoopsSwitchState();
    if (!up) {
      setTimeout(3);
      shooter.setIndexerUp(false);
    }
  }

  protected void execute() {
    if ((controlLoopsOn && !up && shooter.isIndexerDown() ||
        !controlLoopsOn && up) && !conveyorOn) {
      conveyorOn = true;
      shooter.setIndexerUp(true);
      conveyor.setMotor(0.9);
      conveyorTimer.reset();
      conveyorTimer.start();
    }
  }

  protected boolean isFinished() {
    if (controlLoopsOn) {
      return up || conveyorTimer.get() > 0.5 || isTimedOut();
    } else {
      return !up || conveyorTimer.get() > 0.5;
    }
  }

  protected void end() {
    conveyor.setMotor(0);
    if (controlLoopsOn) {
      shooter.setIndexerUp(true);
    }
  }

  protected void interrupted() {
    conveyor.setMotor(0);
    if (controlLoopsOn) {
      shooter.setIndexerUp(true);
    }
  }
}
