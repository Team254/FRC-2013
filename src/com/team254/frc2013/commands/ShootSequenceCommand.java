package com.team254.frc2013.commands;

/**
 * Runs the shooting sequence once.
 * Raises the indexer to prepare for shooting, shoots the loaded disc, sets
 * indexer back down, and runs intake to move a disc into the indexer.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ShootSequenceCommand extends CommandBase {

  boolean doFeed;
  int shotCount = 0;
  public ShootSequenceCommand(boolean doFeed) {
    this.doFeed = doFeed;
    requires(CommandBase.shooter);
    requires(CommandBase.intake);
    requires(CommandBase.conveyor);
   // setTimeout(1.5);
  }

  public ShootSequenceCommand() {
    this(true);
  }

  protected void initialize() {
    sc.wantFedShoot = true;
    shotCount = sc.shotCount;
    System.out.println("shoot sequence init " + shotCount);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return sc.shotCount > shotCount;// || isTimedOut();
  }

  protected void end() {
    System.out.println("shoot sequence end");
    sc.wantFedShoot = false;
  }

  protected void interrupted() {
  }
}
