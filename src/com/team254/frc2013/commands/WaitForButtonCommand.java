package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Wait for a button to be pressed before acting.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class WaitForButtonCommand extends Command{
  Button b;
  public WaitForButtonCommand(Button b) {
    this.b = b;
  }

  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return b.get();
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
