package com.team254.frc2013.commands.auto;

import com.team254.frc2013.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * Runs each drive motor for 5 seconds
 * 
 * @author maskoken@gmail.com (Matthew Koken)
 */

public class DriveMotorTest extends CommandBase{
    private Timer timer = new Timer();
    private int currMotor = 3;
    
    public DriveMotorTest() {
      requires(drive);
    }
  
    protected void initialize() {
      timer.start();
    }

    protected void execute() {
       drive.setMotor(currMotor, 1.0);
       if(timer.get() > 5000000.0) {
         //if uSec, then 5000000
         drive.setMotor(currMotor, 0.0);
         currMotor++;       
         timer.reset();
         timer.start();
       }
    }

    protected boolean isFinished() {
      if(currMotor == 9) {
        return true;
      }
      else {
        return false;
      }
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
