package com.team254.lib.util;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * Analog input wrapper class for the Honeywell PX2AN2XX150PAAAX pressure transducer.
 *
 * @author patrick@ooyala.com (Patrick Fairbank)
 */
public class PressureTransducer {
  private AnalogChannel sensor;

  public PressureTransducer(int port) {
    sensor = new AnalogChannel(port);
  }

  public double getPsi() {
    // Voltage scales linearly: 10% of Vs is 0 psi; 90% is 150 psi.
    return (sensor.getVoltage() - 0.5) / 4 * 150;
  }
}
