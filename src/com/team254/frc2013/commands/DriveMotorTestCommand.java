package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Runs each drive motor for 5 seconds.
 *
 * @author maskoken@gmail.com (Matthew Koken)
 */
public class DriveMotorTestCommand extends CommandBase {
  private Timer timer;
  private int outputPortIndex;

  public DriveMotorTestCommand() {
    requires(drive);
    timer = new Timer();
  }

  protected void initialize() {
    timer.reset();
    timer.start();
    outputPortIndex = 0;
  }

  protected void execute() {
    if (timer.get() < 5.0) {
      switch (outputPortIndex) {
        case 0:
          motors.setLeftDriveA(1.0);
          break;
        case 1:
          motors.setLeftDriveB(1.0);
          break;
        case 2:
          motors.setLeftDriveC(1.0);
          break;
        case 3:
          motors.setRightDriveA(1.0);
          break;
        case 4:
          motors.setRightDriveBC(1.0);
          break;
      }
    } else if (timer.get() < 6.0) {
      motors.driveLR(0, 0);
    } else {
      outputPortIndex++;
      timer.reset();
      timer.start();
    }
  }

  protected boolean isFinished() {
    return outputPortIndex > 4;
  }

  protected void end() {
    timer.stop();
  }

  protected void interrupted() {
  }
}
