package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.commands.CheesyDriveCommand;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.MotionProfile;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.impl.PIDController;
import com.team254.lib.control.impl.ProfiledPIDController;
import com.team254.lib.control.impl.TrapezoidProfile;
import com.team254.lib.util.ChezyGyro;
import com.team254.lib.util.RelativeEncoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Relay;
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

  private Solenoid shifter = new Solenoid(Constants.shifterPort.getInt());
  private Relay relayShifter = new Relay(Constants.compressorRelay.getInt());
  private ChezyGyro gyro = new ChezyGyro(Constants.gyroPort.getInt());
  private boolean isHighGear = true;

  protected class DriveControlSource implements ControlSource {
    boolean straight = true;
    DriveControlSource(boolean straight) {
      this.straight = straight;
    }

    public double get() {
      if (straight) {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
      } else {
        return getGyroAngle();
      }
    }

    public void updateFilter() {
    }

    public boolean getLowerLimit() {
      return false;
    }

    public boolean getUpperLimit() {
      return false;
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
        lastStraight = value;
      } else {
        lastTurn = value;
      }
      setLeftRightPower(lastStraight + lastTurn, lastStraight - lastTurn);
    }
  }

  TrapezoidProfile profile = new TrapezoidProfile(6 * 12.0, .25);
  PIDGains lowStraightGains = new PIDGains(Constants.driveStraightKPLow,
          Constants.driveStraightKILow, Constants.driveStraightKDLow);
  PIDGains highStraightGains = new PIDGains(Constants.driveStraightKPHigh,
          Constants.driveStraightKIHigh, Constants.driveStraightKDHigh);
  ProfiledPIDController straightController = new ProfiledPIDController("straightController",
          highStraightGains,
          new DriveControlSource(true), new DriveControlOutput(true), profile);

  PIDGains lowTurnGains = new PIDGains(Constants.driveTurnKPLow,
          Constants.driveTurnKILow, Constants.driveTurnKDLow);
  PIDGains highTurnGains = new PIDGains(Constants.driveTurnKPHigh,
          Constants.driveTurnKIHigh, Constants.driveTurnKDHigh);
  PIDController turnController = new PIDController("turnController",
          highTurnGains,
          new DriveControlSource(false), new DriveControlOutput(false));;

  public Drive(DriveGearbox motors) {
    super();
    this.motors = motors;
    leftEncoder = new RelativeEncoder(motors.getLeftEncoder());
    rightEncoder = new RelativeEncoder(motors.getRightEncoder());
    leftEncoder.start();
    rightEncoder.start();
    disableControllers();
  }

  protected void initDefaultCommand() {
    setDefaultCommand(new CheesyDriveCommand());
  }

  public void setLeftRightPower(double leftPower, double rightPower) {
    motors.driveLR(leftPower, -rightPower);
  }

  public double getLeftEncoderDistance() {
    return leftEncoder.get() / 256.0 * 3.5 * Math.PI;
  }

  public double getRightEncoderDistance() {
    return rightEncoder.get() / 256.0 * 3.5 * Math.PI;
  }

  public void resetEncoders() {
    motors.getLeftEncoder().reset();
    motors.getRightEncoder().reset();
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  int resets = 0;
  public void resetGyro() {
    System.out.println("Resetting gyro!!!!" + resets++);
    gyro.reset();
  }
  
   public void relayShift(boolean highGear) {
    isHighGear = highGear;
    relayShifter.set(isHighGear ? Relay.Value.kOff : Relay.Value.kForward);
    straightController.setGains(highGear ? highStraightGains : lowStraightGains);
    turnController.setGains(highGear ? highTurnGains : lowTurnGains);
  }
   
  public void shift(boolean highGear) {
    isHighGear = highGear;
    shifter.set(!isHighGear);
    straightController.setGains(highGear ? highStraightGains : lowStraightGains);
    turnController.setGains(highGear ? highTurnGains : lowTurnGains);
  }

  public boolean isHighGear() {
    return isHighGear;
  }

  public void disableControllers() {
    straightController.disable();
    turnController.disable();
    setLeftRightPower(0, 0);
  }

  public void setSpeedGoal(double speed, double angle) {
    profile.setMaxVelocity(Math.abs(speed));
    profile.setTimeToMaxV(0.001);
    straightController.setGoal(speed < 0 ? -1000 : 1000);
    straightController.enable();
    turnController.setGoal(angle);
    turnController.enable();
  }

  public void setPositionGoal(double distance, double angle, double speed) {
    profile.setMaxVelocity(speed);
    profile.setTimeToMaxV(.2);
    straightController.setGoal(distance);
    straightController.enable();
    turnController.setGoal(angle);
    turnController.enable();
  }

  public void updatePositionGoal(double distance) {
    straightController.setGoalRaw(distance);
  }

  public void setTurnGoal(double angle) {
    straightController.disable();
    lastStraight = 0;
    turnController.setGoal(angle);
    turnController.enable();
  }

  public void updateTurnGoal(double angle) {
    turnController.setGoalRaw(angle);
  }

  public boolean onTarget() {
    return straightController.onTarget() && turnController.onTarget();
  }

  public void resetControllers() {
    straightController.setProfile(profile);
  }

  public void setStraightProfile(MotionProfile profile) {
    straightController.setProfile(profile);
  }

  public void reinitGyro() {
    gyro.initGyro();
  }
}
