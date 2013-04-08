package com.team254.frc2013.commands;

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
    // Don't fire the piston if the shooter is not turned on.
    if (shooter.isOn()) {
      shooter.extend();
      shooterTimer.reset();
      shooterTimer.start();
    }
    conveyor.setMotor(-.175);
    intake.setIntakePower(-.1);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return shooterTimer.get() > 0.2 || !shooter.isOn();
  }

  protected void end() {
    System.out.println("RPM of shot: " + shooter.getRpm());
    shooter.retract();
  }

  protected void interrupted() {
  }
}
