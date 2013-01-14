/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Richard
 */
public class TurnCommand extends CommandBase {
    
    double oldAngle;
    double angle;
    double timeout;
    Timer t = new Timer();
    boolean isRight;
    
    public TurnCommand(double angle, double timeout) {
        requires(drive);
        this.angle = angle;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drive.resetGyro();
        oldAngle = drive.getGyroAngle();
        t.start();
        if(angle > 0) {
            isRight = true;
        }
        else {
            isRight = false;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(isRight) {
            drive.driveLR(1.0, -1.0);
        }
        else {
            drive.driveLR(-1.0, 1.0);
        }
        System.out.println("Current angle: " + drive.getGyroAngle() + ", goal: " + angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(t.get() > timeout) {
            System.out.println("Turn has timed out.");
            t.stop();
            return true;
        }
        else if(drive.getGyroAngle() > angle) {
            System.out.println("Turn completed!");
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
