/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.subsystems;

import com.team254.frc2013.RobotMap;
import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.frc2013.commands.TankDriveCommand;
import com.team254.lib.Util;
import com.team254.lib.debug.ThrottledPrinter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Richard
 */
public class Drive extends Subsystem {

    private Victor leftDriveA = new Victor(RobotMap.leftDrivePortA);
    private Victor leftDriveB = new Victor(RobotMap.leftDrivePortB);
    private Victor rightDriveA = new Victor(RobotMap.rightDrivePortA);
    private Victor rightDriveB = new Victor(RobotMap.rightDrivePortB);
    
    private Encoder leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB);
    private Encoder rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightDrivePortB);
    
    double maxSpeed = 1.0;
    
    private ThrottledPrinter printer = new ThrottledPrinter(0.1);
    
    protected void initDefaultCommand() {
        // Choose default drive style here
        //setDefaultCommand(new TankDriveCommand());
        setDefaultCommand(new CheesyDriveCommand());
    }
    
    public void driveLR(double leftPower, double rightPower) {
        leftPower = Util.limit(leftPower, maxSpeed);
        rightPower = Util.limit(rightPower, maxSpeed);
        printer.print("Left Pow: " + leftPower + ", right: " + rightPower + "\nEncoder: " + getLeftEncoderDistance());
        leftDriveA.set(leftPower);
        leftDriveB.set(leftPower);
        rightDriveA.set(-rightPower);
        rightDriveB.set(-rightPower);
    }
    
    public void startEncoders() {
        leftEncoder.start();
        rightEncoder.start();
    }
    
    public double getLeftEncoderDistance() {
        return -leftEncoder.get() / 256.0 * 3.5 * 3.14159265;
    }
    
    public double getRightEncoderDistance() {
        return -rightEncoder.get() / 256.0 * 3.5 * 3.14159265;
    }
    
    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }
    
    public void setMaxSpeed(double speed) {
        maxSpeed = speed;
    }
}
