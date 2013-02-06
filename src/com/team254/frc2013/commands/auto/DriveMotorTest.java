/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Ports 3,4,5,6,7,8
package com.team254.frc2013.commands.auto;

import com.team254.frc2013.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
//import java.util.Timer;

/**
 *
 * @author Matthew Koken
 */

public class DriveMotorTest extends CommandBase{
    Timer timer = new Timer();
    int currMotor = 3;
    public DriveMotorTest() {
      requires(drive);
    }
  
    protected void initialize() {
      timer.start();
    }

    protected void execute() {
       drive.setMotor(currMotor, 1.0);
       if(timer.get() > 5.0) {
         //if uSec, then 5000000
         drive.setMotor(currMotor, 0.0);
         currMotor++;       
         timer.reset();
         timer.start();
       }
    }

    protected boolean isFinished() {
      if(currMotor == 9)
        return true;
      else
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
