package com.team254.frc2013.control;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This class is designed to handle the case where there is a Subsystem
 * which uses a single PIDController almost constantly.
 *
 * It provides some convenience methods to run an internal PIDController.
 * It also allows access to the internal PIDController in order to give total
 * control to the programmer.
 *
 * @author Joe Grinstead
 */
public abstract class ProfiledPIDSubsystem extends Subsystem implements Sendable {

    private ProfiledPIDController controller;
    private PIDOutput output = new PIDOutput() {

        public void pidWrite(double output) {
            usePIDOutput(output);
        }
    };
    
    private ProfiledPIDSource source = new ProfiledPIDSource() {

        public double pidGet() {
            return returnPIDInput();
        }
    };
    
    /**
     * Constructors for this ProfiledPIDSubsystem.
     */
    public ProfiledPIDSubsystem(String name, double p, double i, double d) {
        super(name);
        controller = new ProfiledPIDController(p, i, d, source, output);
    }

    public ProfiledPIDSubsystem(String name, double p, double i, double d, double f) {
        super(name);
        controller = new ProfiledPIDController(p, i, d, f, source, output);
    }

    public ProfiledPIDSubsystem(String name, double p, double i, double d, double f, double period) {
        super(name);
        controller = new ProfiledPIDController(p, i, d, f, source, output, period);
    }

    public ProfiledPIDSubsystem(double p, double i, double d) {
        controller = new ProfiledPIDController(p, i, d, source, output);
    }

    public ProfiledPIDSubsystem(double p, double i, double d, double period, double f) {
        controller = new ProfiledPIDController(p, i, d, f, source, output, period);
    }

    public ProfiledPIDSubsystem(double p, double i, double d, double period) {
        controller = new ProfiledPIDController(p, i, d, source, output, period);
    }

    /**
     * Returns the ProfiledPIDController used by this ProfiledPIDSubsystem.
     */
    public ProfiledPIDController getPIDController() {
        return controller;
    }


    /**
     * Adds the given value to the setpoint.
     */
    public void setSetpointRelative(double deltaSetpoint) {
        setSetpoint(getPosition() + deltaSetpoint);
    }

    /**
     * Sets the setpoint to the given value.
     */
    public void setSetpoint(double setpoint) {
        controller.setSetpoint(setpoint);
    }

    /**
     * Returns the setpoint.
     */
    public double getSetpoint() {
        return controller.getSetpoint();
    }

    /**
     * Returns the current position
     */
    public double getPosition() {
        return returnPIDInput();
    }
    
    /**
     * Sets the maximum and minimum values expected from the input.
     */
    public void setInputRange(double minimumInput, double maximumInput) {
        controller.setInputRange(minimumInput, maximumInput);
    }
    
     /**
     * Set the absolute error considered tolerable for use with OnTarget.
     */
   public void setAbsoluteTolerance(double t) {
        controller.setAbsoluteTolerance(t);
    }
   
     /**
     * Set the percentage error considered tolerable for use with OnTarget.
     */
   public void setPercentTolerance(double p) {
       controller.setPercentTolerance(p);
   }
    
    /**
     * Return true if the error is within the percentage of the total input range.
     */
    public boolean onTarget() {
        return controller.onTarget();
    }

    /**
     * Returns the input for the PID loop.
     */
    protected abstract double returnPIDInput();

    /**
     * Uses the value that the PID loop calculated.
     */
    protected abstract void usePIDOutput(double output);

    /**
     * Enables the internal ProfiledPIDController.
     */
    public void enable() {
        controller.enable();
    }

    /**
     * Disables the internal ProfiledPIDController.
     */
    public void disable() {
        controller.disable();
    }

    /**
     * Returns the Smart Dashboard type of this class.
     */
    public String getSmartDashboardType(){
        return "ProfiledPIDSubsystem";
    }
    
    /**
     * Initializes the table.
     */
    public void initTable(ITable table){
        controller.initTable(table);
        super.initTable(table);
    }
}
