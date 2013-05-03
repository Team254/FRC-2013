package com.team254.frc2013;

import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.frc2013.commands.PtoCommand;

/**
 * Maps operator control buttons to a specified command.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OperatorControlHelper {
  public static void setupOperationMap(ControlBoard c) {
    c.operatorJoystick.ptoOnSwitch.whenPressed(new PtoCommand());
    c.operatorJoystick.ptoOnSwitch.whenReleased(new CheesyDriveCommand());
  }
}
