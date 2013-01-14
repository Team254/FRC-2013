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
public class DriveDistanceCommand extends CommandBase {
    
    private double distance;
    private double maxSpeed;
    private double timeout;
    Timer t = new Timer();
    
    public DriveDistanceCommand(double distance, double maxSpeed, double timeout) {
        this.distance = distance;
        this.maxSpeed = maxSpeed;
        this.timeout = timeout;
        requires(drive);
    }
    
    protected void initialize() {
        drive.startEncoders();
        drive.resetEncoders();
        drive.setMaxSpeed(maxSpeed);
        t.start();
    }

    protected void execute() {
        drive.driveLR(1.0, 1.0);
    }

    protected boolean isFinished() {
        if(t.get() >= timeout) {
            System.out.println("DriveDistance timed out.");
            return true;
        }
        else if(drive.getLeftEncoderDistance() > distance || drive.getRightEncoderDistance() > distance) {
            return true;
        }
        return false;
    }

    protected void end() {
        drive.setMaxSpeed(1.0);
        drive.driveLR(0.0, 0.0);
        drive.resetEncoders(); 
        t.stop();
    }

    protected void interrupted() {
    }
}
