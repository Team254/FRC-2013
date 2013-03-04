package com.team254.frc2013.commands;

import com.team254.frc2013.subsystems.Hanger;

/**
 * Controls the first-stage hanger hook
 *
 * @author richard@team254.com (Richard Lin)
 */
public class HangerHookCommand extends CommandBase {
  private boolean isUp;

  public HangerHookCommand(boolean isUp) {
    this.isUp = isUp;
  }

  protected void initialize() {
    if (isUp) {
      hanger.setHookUp(Hanger.HANGER_HOOK_EXTENDED);
    } else {
      hanger.setHookUp(Hanger.HANGER_HOOK_FLOATING);
    }
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
