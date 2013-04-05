/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author tombot
 */
public class WaitForDiscCommand extends CommandBase {
  
  public WaitForDiscCommand(double timeout) {
    setTimeout(timeout);
  }

  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return !shooter.isIndexerDown() || shooter.isIndexerLoaded() || isTimedOut(); // indexer up OR disk sensor OR timeout
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
