/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author Richard
 */
public class DriveDistanceCommand extends CommandBase {
    
    private double distance;
    private double maxSpeed;
    private double timeout;
    
    public DriveDistanceCommand(double distance, double maxSpeed, double timeout) {
        this.distance = distance;
        this.maxSpeed = maxSpeed;
        this.timeout = timeout;
        requires(drive);
    }
    
    protected void initialize() {
        setTimeout(timeout);
        drive.startEncoders();
        drive.resetEncoders();
        drive.setMaxSpeed(maxSpeed);
    }

    protected void execute() {
        drive.driveLR(1.0, 1.0);
    }

    protected boolean isFinished() {
        if(drive.getLeftEncoderDistance() > distance || drive.getRightEncoderDistance() > distance) {
            return true;
        }
        return false;
    }

    protected void end() {
        drive.setMaxSpeed(1.0);
    }

    protected void interrupted() {
    }
}
