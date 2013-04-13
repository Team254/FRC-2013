package com.team254.frc2013;

import com.team254.lib.control.StateSpaceGains;

public class ShooterGains {
  static public StateSpaceGains[] getGains() {
    return new StateSpaceGains[] {
        new StateSpaceGains(
            new double[] {1.000000, 0.000000, 0.795701, 0.984297},  // A
            new double[] {1.000000, 0.795701},  // B
            new double[] {0.000000, 1.000000},  // C
            new double[] {0.000000},  // D
            new double[] {0.150810, 0.934297},  // L
            new double[] {0.786650, 0.311232},  // K
            new double[] {12.000000},  // Umax
            new double[] {-2.000000}),  // Umin
        };  
  }
}
