package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.impl.PIDController;
import com.team254.lib.control.impl.ProfiledPIDController;
import com.team254.lib.util.RelativeEncoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class representing the drive train and managing its motors and sensors.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Drive extends Subsystem {

  private DriveGearbox motors;
  RelativeEncoder leftEncoder;
  RelativeEncoder rightEncoder;

  // Shifter
  private Solenoid shifter = new Solenoid(Constants.shifterPort.getInt());
  private Gyro gyro = new Gyro(1);//Constants.gyroPort.getInt());
  private boolean isHighGear = true;

  protected class DriveControlSource implements ControlSource {
    boolean straight = true;
    DriveControlSource(boolean straight) {
      this.straight = straight;
    }

    public double get() {
      // This is super hacky.
      if (straight) {
        //System.out.println("e: " + (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0);
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
      } else {
        return getGyroAngle();
      }
    }

    public void updateFilter() {
    }
  }

  double lastStraight = 0;
  double lastTurn = 0;
  protected class DriveControlOutput implements ControlOutput {
    boolean straight;
    DriveControlOutput(boolean straight) {
      this.straight = straight;
    }

    public void set(double value) {
      if (straight) {
        lastStraight = value; // Why is this negative?
      } else {
        lastTurn = value;
      }
      setLeftRightPower(lastStraight + lastTurn, lastStraight - lastTurn);
    }
  }

  ProfiledPIDController straightController = new ProfiledPIDController("straightController",
          new PIDGains(Constants.driveStraightKP, Constants.driveStraightKI, Constants.driveStraightKD),
          new DriveControlSource(true), new DriveControlOutput(true),
          6*12.0, .5); // Half a second to accelerate to 5.0 ft/s

  PIDController turnController = new PIDController("turnController",
          new PIDGains(Constants.driveTurnKP, Constants.driveTurnKI, Constants.driveTurnKD),
          new DriveControlSource(false), new DriveControlOutput(false));;

  public Drive(DriveGearbox motors) {
    super();
    this.motors = motors;
    leftEncoder = new RelativeEncoder(motors.getLeftEncoder());
    rightEncoder = new RelativeEncoder(motors.getRightEncoder());
    leftEncoder.start();
    rightEncoder.start();
    openLoop();
  }

  protected void initDefaultCommand() {
    //setDefaultCommand(new TankDriveCommand());
    setDefaultCommand(new CheesyDriveCommand());
  }

  public void setLeftRightPower(double leftPower, double rightPower) {
    motors.driveLR(leftPower, -rightPower); // changed from + - for comp bot
  }

  public void setMotor(int portNumber, double power) {
    motors.setMotor(portNumber, power);
  }

  public double getLeftEncoderDistance() {
    return leftEncoder.get() / 256.0 * 3.5 * Math.PI;
  }

  public double getRightEncoderDistance() {
    return rightEncoder.get() / 256.0 * 3.5 * Math.PI;
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void setMaxSpeed(double speed) {
    straightController.setMaxVelocity(speed);
  }

  public void shift(boolean highGear) {
    isHighGear = highGear;
    shifter.set(isHighGear);
  }

  public boolean isHighGear() {
    return isHighGear;
  }

  public void openLoop() {
    straightController.disable();
    turnController.disable();
    setLeftRightPower(0,0);
  }

  public void setGoal(double distance, double angle) {
    straightController.setGoal(distance);
    if (distance != 0)
      straightController.enable();
    else
      straightController.disable();
    turnController.setGoal(angle);
    turnController.enable();
  }

  public boolean onTarget() {
    return straightController.onTarget() && turnController.onTarget();
  }
}
