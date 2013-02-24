package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * Tests all robot subsystems to make sure they are working
 *
 * @author tom@team254.com (Tom Bottiglieri) 
 */
public class TestOutputsCommand extends CommandBase {
  double val;
  double diff = 0.01;
  int i = 0;

  public TestOutputsCommand() {
    requires(shooter);
    requires(motors);
    requires(intake);
    requires(conveyor);
    requires(hanger);
  }

  protected void initialize() {

  }

  protected void execute() {
    /*System.out.println("in test " + val);
    /*val += diff;
    if (val > .99) {
      diff = -diff;
    }
    else if (val < -.99)
      diff = -diff;
    */
    
    //val = 1.0;
    //motors.driveLR(val, val);
    
   // intake.setIntakePower(val);
    //intake.raiseIntake(val);
    
    //conveyor.setMotor(val);
    drive.shift(true);
    shooter.setHighAngle(true);
    shooter.extend();
    hanger.setHookUp(Value.kReverse);
    hanger.setPto(true);
  //  indexer.setPistonDown(true);
    conveyor.setSolenoidState(true);
    
    //shooter.setRawPwm(1);
    //shooter.setSpeed(10000);
  }

  protected boolean isFinished() {
    return false;
    
  }

  protected void end() {
    //shooter.setSpeed(0);
  }

  protected void interrupted() {
  }
  
}
