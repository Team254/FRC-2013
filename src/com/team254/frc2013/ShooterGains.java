package com.team254.frc2013;

import com.team254.lib.control.StateSpaceGains;

public class ShooterGains {
  static public StateSpaceGains[] getGains() {
    return new StateSpaceGains[] {
        new StateSpaceGains(
            new double[] {1.000000, 0.000000, 4.054402, 0.965582},  // A
            new double[] {1.000000, 4.054402},  // B
            new double[] {0.000000, 1.000000},  // C
            new double[] {0.000000},  // D
            new double[] {0.012332, 0.565582},  // L
            new double[] {0.728143, 0.033899},  // K
            new double[] {12.000000},  // Umax
            new double[] {-2.000000}),  // Umin

        };
  }
}
