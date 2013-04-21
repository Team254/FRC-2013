/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import com.team254.lib.util.Debouncer;

/**
 *
 * @author tombot
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
    boolean timedOut = CommandBase.controlBoard.operatorJoystick.getControlLoopsSwitchState() && isTimedOut();
    if (indexerUp || sensor || timedOut || !CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState())  // indexer up OR disk sensor OR timeout
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
