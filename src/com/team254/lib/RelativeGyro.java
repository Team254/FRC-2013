/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib;

import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author Richard
 */
public class RelativeGyro extends Gyro {
    
    double offset;
    
    public RelativeGyro(int port) {
        super(port);
    }
    
    public double getAbsoluteAngle() {
        return super.getAngle();
    }

    public double getAngle() {
        return getAbsoluteAngle() - offset;
    }

    public void reset() {
        offset = getAbsoluteAngle();
    }

    public void resetAbsolute() {
        super.reset();
    }
}
