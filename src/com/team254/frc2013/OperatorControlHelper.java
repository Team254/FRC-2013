package com.team254.frc2013;

import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.frc2013.commands.ContinuousShootCommand;
import com.team254.frc2013.commands.IntakeRaiseCommand;
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
    c.operatorJoystick.getIntakeUpButton().whenPressed(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_UP));
    c.operatorJoystick.getIntakeUpButton().whenReleased(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_OFF));
    c.operatorJoystick.getIntakeDownButton().whenPressed(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    c.operatorJoystick.getIntakeDownButton().whenReleased(
        new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_OFF));

    c.operatorJoystick.getShootButton().whenPressed(new ShootSequenceCommand());
    c.operatorJoystick.getIndexButton().whenPressed(new SetIndexerDownCommand(false));
    c.operatorJoystick.getIndexButton().whenReleased(new SetIndexerDownCommand(true));

    c.operatorJoystick.getIntakeButton().whenPressed(new RunIntakeCommand(1));
    c.operatorJoystick.getIntakeButton().whenReleased(new RunIntakeCommand(0));
    c.operatorJoystick.getIntakeOutButton().whenPressed(new RunIntakeCommand(-1));
    c.operatorJoystick.getIntakeOutButton().whenReleased(new RunIntakeCommand(0));

    c.operatorJoystick.getRapidFireButton().whenPressed(new ContinuousShootCommand());
    c.operatorJoystick.getRapidFireButton().whenReleased(new ResetRapidFireCommand());

    c.operatorJoystick.ptoOnSwitch.whenPressed(new PtoCommand());
    c.operatorJoystick.ptoOnSwitch.whenReleased(new CheesyDriveCommand());
  }
}
