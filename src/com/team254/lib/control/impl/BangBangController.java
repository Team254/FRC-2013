package com.team254.lib.control.impl;

import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.Controller;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * A controller that runs while below a certain goal, stops when goal is reached.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class BangBangController extends Controller implements NamedSendable {
  private ControlSource source;
  private ControlOutput output;
  private double goal;
  
  public BangBangController(String name, ControlSource source, ControlOutput output) {
    super(name);
    this.source = source;
    this.output = output;
  }

  public void update() {
   
    if (!isEnabled())
      return;
    source.updateFilter();
    double s = source.get();
        SmartDashboard.putNumber(name + " rpm", s);
    SmartDashboard.putNumber(name + " goal", goal);
    if (s >= goal) {
      output.set(0.0);
    }
    else {
      output.set(1.0);
    }
  }

  public void setGoal(double goal) {
    this.goal = goal;
  }

  private ITable table;
  public void initTable(ITable table) {
    this.table = table;
    if(table != null) {
      table.putNumber("goal", goal);
      table.putNumber("source", source.get());
      table.putBoolean("enabled", isEnabled());
    }
  }

  public ITable getTable() {
    return table;
  }

  public String getSmartDashboardType() {
    return "BangBangController";
  }

  public double getGoal() {
    return this.goal;
  }
}
