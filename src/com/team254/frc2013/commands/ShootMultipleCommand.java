package com.team254.frc2013.commands;

import com.team254.frc2013.Messages;
import com.team254.lib.util.Listener;
import com.team254.lib.util.Notifier;

/**
 *
 * @author tombot
 */
public class ShootMultipleCommand extends CommandBase implements Listener {
  int num;
  int taken = 0;
  public ShootMultipleCommand(int num) {
    this.num = num;
    requires(shooter);
    setTimeout(num * 1.2);
  }


  protected void initialize() {
    Notifier.subscribe(Messages.SHOT_TAKEN, this);
  }

  protected void execute() {
    shooter.tryShoot();
  }

  protected boolean isFinished() {
    return (taken >= num) || isTimedOut();
  }

  protected void end() {
    taken = 0;
  }

  protected void interrupted() {
  }

  public void receive(int key, double value) {
    if (key == Messages.SHOT_TAKEN) {
      taken++;
    }
  }
}
