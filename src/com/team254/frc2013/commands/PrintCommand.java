/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author tombot
 */
public class PrintCommand extends CommandBase {
  String s;
  
  public PrintCommand(String s){
    this.s = s;
  }

  protected void initialize() {
  }

  protected void execute() {
    System.out.println(s);
  }
  

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
  
}
