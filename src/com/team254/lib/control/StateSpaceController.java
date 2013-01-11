/*
 * FIRST Team 254 - The Cheesy Poofs
 * Team 254 Lib
 * Control
 * StateSpaceController
 *
 * This class contains the control logic for a State Space Controller.
 */

package com.team254.lib.control;

import com.team254.lib.math.Matrix;


/**
 *
 * @author Tom Bottiglieri
 */
public abstract class StateSpaceController extends PeriodicController {
  int numInputs;
  int numOutputs;
  int numStates;
  boolean initialized = false;
  StateSpaceGains gains;
  protected double rate = 50.0;
  
  //the state matrices, calculated and imported from matlab
  protected Matrix A;
  protected Matrix B;
  protected Matrix C;
  protected Matrix D;
  protected Matrix L;
  protected Matrix K;

  // Other state matrices
  protected Matrix X;
  protected Matrix Xhat;
  protected Matrix U;
  protected Matrix Umin;
  protected Matrix Umax;


  public StateSpaceController(int nIn, int nOut, int nStates, StateSpaceGains ssg, double period) {
    super(period);
    numInputs = nIn;
    numOutputs = nOut;
    numStates = nStates;
    gains = ssg;

    // Size the matrices
    A = new Matrix(numStates, numStates);
    B = new Matrix(numStates, numOutputs);
    C = new Matrix(numOutputs, numStates);
    D = new Matrix(numOutputs, numOutputs);
    L = new Matrix(numStates, numOutputs);
    K = new Matrix(numOutputs, numStates);
    X = new Matrix(numStates, 1);
    Xhat = new Matrix(numStates, 1);
    U = new Matrix(numOutputs, 1);
    Umin = new Matrix(numOutputs, 1);
    Umax = new Matrix(numOutputs, 1);

  }

  private void updateGains() {
    A.flash(gains.getA());
    B.flash(gains.getB());
    C.flash(gains.getC());
    D.flash(gains.getD());
    K.flash(gains.getK());
    L.flash(gains.getL());
    Umin.flash(gains.getUmax());
    Umax.flash(gains.getUmin());
  }

  public void updateSSC(Matrix R, Matrix Y) {
    if (gains.updated()){
      updateGains();
    }

    Matrix r1 = Matrix.subtract(R, Xhat);
    U = Matrix.multiply(K, r1);

    for(int i=0; i < numOutputs; i++) {
      double u_i = U.get(i);
      double u_max = Umin.get(i);
      double u_min = Umax.get(i);
      if (u_i > u_max) {
        u_i = u_max;
      } else if (u_i < u_min) {
        u_i = u_min;
      }
      U.set(i, u_i);
    }
    
    // X_hat = (A - L * C) * X_hat + L * Y + B * U;
    Matrix b_u = Matrix.multiply(B, U);
    Matrix l_y = Matrix.multiply(L, Y);
    Matrix l_c = Matrix.multiply(L, C);
    Matrix a_lc = Matrix.subtract(A, l_c);
    Matrix alc_xhat = Matrix.multiply(a_lc, Xhat);
    Matrix xhatp1 = Matrix.add(alc_xhat, l_y);
    Xhat = Matrix.add(xhatp1, b_u);
  }

  public Matrix getXhat() {
    return Xhat;
  }

}
