package com.team254.frc2013.commands;

/**
 * Loads a disc into the shooter and fires it.
 *
 * @author tom@team254.com (Tom Bottiglieri) 
 */
public class ShootCommand extends CommandBase {

  public ShootCommand() {
    requires(shooter);
  }
  
  protected void initialize() {
    System.out.println("SHOOTING!");
    //setTimeout(.3);
  }

  protected void execute() {
    shooter.tryShoot();
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
  
}
