package com.team254.frc2013.commands;

import com.team254.lib.util.Debouncer;

/**
 * Checks if a disc is sensed in the indexer.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class WaitForDiscCommand extends CommandBase {

  Debouncer d = new Debouncer(.05);
  public WaitForDiscCommand(double timeout) {
    setTimeout(timeout);
  }

  protected void initialize() {
    d.reset();
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    boolean indexerUp = !shooter.isIndexerSetDown();
    boolean sensor = d.update(shooter.isIndexerLoaded()) ;
    boolean timedOut = CommandBase.controlBoard.operatorJoystick.getControlLoopsSwitchState() &&
            isTimedOut();
    // check if indexer up OR disk sensor OR timeout
    if (indexerUp || sensor || timedOut ||
            !CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState())
    {
      System.out.println("Has disc: " + indexerUp + " " + sensor + " " + timedOut);
      return true;
    }
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
