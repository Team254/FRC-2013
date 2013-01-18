package com.team254.lib;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bg
 */

public class Constant {
  //variable
  private String name;
  private Double value;
  
  //constructor
  public Constant(String name, double value) {
    this.name = name;
    this.value = new Double(value);
  }

  
  //functions
  public Double getVal() {
    return value;
  }
  
  public String getName(){
    return name;
  }
  public void setVal(Double value){
    this.value = value;
  }

  public Constant create(String name, double value) {
    return new Constant(name, value);
  }
}