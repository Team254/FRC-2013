package com.team254.frc2013.commands;

/**
 * Stow the intake when preparing to hang.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
class StowIntakeForHangCommand extends CommandBase {

  public StowIntakeForHangCommand() {
  }

  protected void initialize() {
    sc.wantHang = true;
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
