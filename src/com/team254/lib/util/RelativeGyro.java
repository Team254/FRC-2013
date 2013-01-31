package com.team254.lib.util;

import edu.wpi.first.wpilibj.Gyro;

/**
 *
 * @author richard@team254.com (Richard Lin)
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
