/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.control;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 *
 * @author Travus
 */
public class PIDController extends Controller implements Sendable {
  PIDGains gains;
  ControlSource source;
  ControlOutput output;
  boolean enabled;
  double goal;
  double errorSum;
  double lastError;

  public PIDController(String name, PIDGains gains, ControlSource source, ControlOutput output) {
    super(name);
    this.gains = gains;
    this.source = source;
    this.output = output;
    enabled = true;
    errorSum = 0.0;
    lastError = 0.0;
  }
  
  public void enable() {
    enabled = true;
  }
  
  public boolean isEnabled() {
    return enabled;
  }

  public void disable() {
    enabled = false;
  }

  public void update() {
    double error = goal - source.get();
    double p = gains.getP() * error;
    errorSum += error;
    double i = gains.getI() * errorSum;
    double dError = error - lastError;
    double d = gains.getD() * dError;
    lastError = error;
    output.set(p + i + d);
  }

  public void setGoal(double goal) {
    this.goal = goal;
  }
  
  public double getGoal(double goal) {
    return this.goal;
  }

  private ITableListener listener = new ITableListener() {
    public void valueChanged(ITable table, String key, Object value, boolean isNew) {
      if (key.equals("p") || key.equals("i") || key.equals("d") || key.equals("f")) {
        if (gains.getP() != table.getNumber("p", 0.0) || gains.getI() != table.getNumber("i", 0.0) ||
          gains.getD() != table.getNumber("d", 0.0))
          gains.set(table.getNumber("p", 0.0), table.getNumber("i", 0.0), table.getNumber("d", 0.0));
      } else if (key.equals("setpoint")) {
        if (goal != ((Double) value).doubleValue())
          setGoal(((Double) value).doubleValue());
      } else if (key.equals("enabled")) {
          if (isEnabled() != ((Boolean) value).booleanValue()) {
            if (((Boolean) value).booleanValue()) {
              enable();
            } else {
             disable();
            }
          }
      }
    }
  };

  private ITable table;
  public void initTable(ITable table) {
    if(this.table!=null)
      this.table.removeTableListener(listener);
    this.table = table;
    if(table!=null){
      table.putNumber("p", gains.getP());
      table.putNumber("i", gains.getI());
      table.putNumber("d", gains.getD());
      table.putNumber("goal", goal);
      table.putBoolean("enabled", isEnabled());
      table.addTableListener(listener, false);
    }
  }

    public ITable getTable() {
      return table;
    }

    public String getSmartDashboardType() {
      return "PIDController";
    }
}
