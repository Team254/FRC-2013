package com.team254.frc2013;

import com.team254.frc2013.commands.ConveyorSpeedCommand;
import com.team254.frc2013.commands.HangerHookCommand;
import com.team254.frc2013.commands.IncrementShooterSpeedCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.SetIntakePositionCommand;
import com.team254.frc2013.commands.ShootCommand;
import com.team254.frc2013.commands.ShooterSpeedCommand;
import com.team254.frc2013.commands.ToggleShooterAngleCommand;



/**
 * Maps operator control buttons to a specified command.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OperatorControlHelper {

  public static void setupOperationMap(ControlBoard c) {
    c.operatorJoystick.getFarButton().whenPressed(new SetIntakePositionCommand(105));
    c.operatorJoystick.getMiddleButton().whenPressed(new SetIntakePositionCommand(80));
    c.operatorJoystick.getCloseButton().whenPressed(new SetIntakePositionCommand(45));
    c.operatorJoystick.getShootButton().whenPressed(new SetIntakePositionCommand(0));

    //c.operatorJoystick.getHang30Button().whenPressed(new ShootCommand());
   // c.operatorJoystick.getHang30Button().whenPressed(new ShootCommand());
    //c.operatorJoystick.getHang20Button().whenPressed(new ToggleShooterAngleCommand());
    
    c.operatorJoystick.getFarButton().whenPressed(new ShooterSpeedCommand(13000, false));
    //c.operatorJoystick.getMiddleButton().whenPressed(new PrintCommand("Mid"));
    c.operatorJoystick.getCloseButton().whenPressed(new ShooterSpeedCommand(13000, true));
    c.operatorJoystick.getShootButton().whenPressed(new ShootCommand());

    c.operatorJoystick.getConveyorButton().whenPressed(new ConveyorSpeedCommand(1.0));
    c.operatorJoystick.getConveyorButton().whenReleased(new ConveyorSpeedCommand(0.0));
    c.operatorJoystick.getConveyOutButton().whenPressed(new ConveyorSpeedCommand(-1.0));
    c.operatorJoystick.getConveyOutButton().whenReleased(new ConveyorSpeedCommand(0.0));

    c.operatorJoystick.getIntakeOutButton().whenPressed(new RunIntakeCommand(-1.0));
    c.operatorJoystick.getIntakeOutButton().whenReleased(new RunIntakeCommand(0.0));
    c.operatorJoystick.getIntakeButton().whenPressed(new RunIntakeCommand(1.0));
    c.operatorJoystick.getIntakeButton().whenReleased(new RunIntakeCommand(0.0));

    c.operatorJoystick.getAutonSelectButton().whenPressed(new HangerHookCommand(true));
    c.operatorJoystick.getAutonSelectButton().whenReleased(new HangerHookCommand(false));

    c.operatorJoystick.getShooterOnSwitch().whenActive(new ShooterSpeedCommand(13000));
    c.operatorJoystick.getShooterOnSwitch().whenInactive(new ShooterSpeedCommand(0));
    
  /*  c.operatorJoystick.getIntakeUpSwitch().whenActive(new SetIntakePositionCommand(115));
    c.operatorJoystick.getIntakeUpSwitch().whenReleased(new SetIntakePositionCommand(90));
    c.operatorJoystick.getIntakeDownSwitch().whenReleased(new SetIntakePositionCommand(90));
    c.operatorJoystick.getIntakeDownSwitch().whenActive(new SetIntakePositionCommand(0));

    /*
    c.operatorJoystick.getFarButton().whenPressed(new PrintCommand("Far"));
    c.operatorJoystick.getMiddleButton().whenPressed(new PrintCommand("Mid"));
    c.operatorJoystick.getCloseButton().whenPressed(new PrintCommand("Close"));
    c.operatorJoystick.getShootButton().whenPressed(new PrintCommand("Shoot"));

    c.operatorJoystick.getHang30Button().whenPressed(new PrintCommand("Clumb 30"));
    c.operatorJoystick.getHang20Button().whenPressed(new PrintCommand("Hang 10"));

    c.operatorJoystick.getConveyorButton().whenPressed(new PrintCommand("convey"));
   // c.operatorJoystick.getConveyorButton().whenReleased(new ConveyorSpeedCommand(0.0));
    c.operatorJoystick.getConveyOutButton().whenPressed(new PrintCommand("convey out"));
    //c.operatorJoystick.getConveyOutButton().whenReleased(new ConveyorSpeedCommand(0.0));

    c.operatorJoystick.getIntakeOutButton().whenPressed(new PrintCommand("intake out"));
   // c.operatorJoystick.getIntakeOutButton().whenReleased(new PrintCommand("Far"));
    c.operatorJoystick.getIntakeButton().whenPressed(new PrintCommand("intout"));
   // c.operatorJoystick.getIntakeButton().whenReleased(new IntakeSpeedCommand(0.0));

    c.operatorJoystick.getAutonSelectButton().whenPressed(new PrintCommand("auton select"));
   // c.operatorJoystick.getAutonSelectButton().whenReleased(new HangerHookCommand(false));
    c.operatorJoystick.getShooterOnSwitch().whenPressed(new PrintCommand("shooter on"));
    c.operatorJoystick.getStartHangSwitch().whenPressed(new PrintCommand("start hang"));
    c.operatorJoystick.getControlLoopsSwitch().whenPressed(new PrintCommand("control loops"));
    c.operatorJoystick.getIntakeUpSwitch().whenPressed(new PrintCommand("intake up"));
    c.operatorJoystick.getIntakeDownSwitch().whenPressed(new PrintCommand("intake down"));
    * */
  }
}
