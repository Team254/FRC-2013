package com.team254.frc2013.commands;

import com.team254.lib.util.Logger;
import edu.wpi.first.wpilibj.Timer;

/**
 * Shoots a disc that is already loaded into the shooter.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShootCommand extends CommandBase {
  private Timer shooterTimer;

  public ShootCommand() {
    requires(shooter);
    shooterTimer = new Timer();
  }

  protected void initialize() {
    shooter.extend();
    shooterTimer.reset();
    shooterTimer.start();
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return shooterTimer.get() > 0.2;
  }

  protected void end() {
    System.out.println("RPM of shot: " + shooter.getRpm());
    shooter.retract();
  }

  protected void interrupted() {
  }
}
