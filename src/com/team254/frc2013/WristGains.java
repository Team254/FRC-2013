package com.team254.frc2013;

import com.team254.lib.control.StateSpaceGains;

public class WristGains {
  static public StateSpaceGains[] getGains() {
    return new StateSpaceGains[] {
        new StateSpaceGains(
            new double[] {1.000000, 0.009143, 0.000000, 0.833735},  // A
            new double[] {0.000298, 0.057796},  // B
            new double[] {1.000000, 0.000000},  // C
            new double[] {0.000000},  // D
            new double[] {1.733735, 67.184939},  // L
            new double[] {173.022768, 8.343521},  // K
            new double[] {12.000000},  // Umax
            new double[] {-12.000000}),  // Umin
        };
  }
}
