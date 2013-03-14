package com.team254.frc2013;

import com.team254.frc2013.commands.ContinuousShootCommand;
import com.team254.frc2013.commands.IndexerCommand;
import com.team254.frc2013.commands.IntakeRaiseCommand;
import com.team254.frc2013.commands.ShootCommand;

/**
 * Maps operator control buttons to a specified command.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OperatorControlHelper {
  public static void setupOperationMap(ControlBoard c) {
    c.operatorJoystick.getIntakeUpButton().whenPressed(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_UP));
    c.operatorJoystick.getIntakeUpButton().whenReleased(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_OFF));
    c.operatorJoystick.getIntakeDownButton().whenPressed(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    c.operatorJoystick.getIntakeDownButton().whenReleased(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_OFF));

    c.operatorJoystick.getIndexButton().whenPressed(new IndexerCommand(false));
    c.operatorJoystick.getIndexButton().whenReleased(new IndexerCommand(true));

    c.operatorJoystick.getShootButton().whenPressed(new ShootCommand());


    c.operatorJoystick.getRapidFireButton().whenPressed(new ContinuousShootCommand(true));
    c.operatorJoystick.getRapidFireButton().whenReleased(new ContinuousShootCommand(false));

    // NOTE(pat, 2013-03-03): PTO disabled for safety until the hanger works.
    //c.operatorJoystick.ptoOnSwitch.whenPressed(new PtoCommand());
    //c.operatorJoystick.ptoOnSwitch.whenReleased(new CheesyDriveCommand());
  }
}
