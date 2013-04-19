package com.team254.frc2013.commands;

/**
 * Reversed the conveyor if the indexer cannot make it down
 * 
 * @author tom@team254.com
 */
public class FixIndexerCommand extends CommandBase {

  public FixIndexerCommand(double timeout) {
    requires(intake);
    requires(conveyor);
    setTimeout(timeout);
  }

  public FixIndexerCommand() {
    this(.25);
  }

  protected void initialize() {
    shooter.setIndexerUp(false);
    intake.setIntakePower(0);
    conveyor.setMotor(0);
  }

  protected void execute() {
    if(!shooter.isIndexerSensedDown()) {
      conveyor.setMotor(-1);
      intake.setIntakePower(-1);
    } else {
      conveyor.setMotor(0);
      intake.setIntakePower(0);
    }
  }

  protected boolean isFinished() {
    return shooter.isIndexerSensedDown() || isTimedOut();
  }

  protected void end() {
    conveyor.setMotor(0);
    intake.setIntakePower(0);
  }

  protected void interrupted() {
  }
}
