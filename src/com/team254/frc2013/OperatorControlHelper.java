package com.team254.frc2013;

import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.frc2013.commands.SetIntakeDownCommand;
import com.team254.frc2013.commands.PtoCommand;
import com.team254.frc2013.commands.ResetRapidFireCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.SetIndexerDownCommand;
import com.team254.frc2013.commands.ShootSequenceCommand;

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
