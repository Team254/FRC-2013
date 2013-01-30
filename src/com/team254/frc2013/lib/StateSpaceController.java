package com.team254.frc2013.lib;

import com.team254.frc2013.lib.Matrix;
/**
 *
 * @author Matthew Koken
 * @author Dorian Chan
 * @author Arthur Kalb
 * 
 */
public abstract class StateSpaceController {
    int numIn;
    int numOut;
    int numStates;
    StateSpaceGains gains;
    double rate = 50.0;
    
    //State matrices
    //Todo: rename matrices
    protected Matrix A;
    protected Matrix B;
    protected Matrix C;
    protected Matrix D;
    protected Matrix L;
    protected Matrix K;
    protected Matrix R;
    protected Matrix Y;
    //Additional state matrices
    protected Matrix X;
    protected Matrix X_hat;
    protected Matrix U;
    protected Matrix U_max;
    protected Matrix U_min;
    protected Matrix b_u;
    protected Matrix l_y;
    protected Matrix l_c;
    protected Matrix a_lc;
    protected Matrix alc_xhat;
    protected Matrix xhatp1;
    protected Matrix U_temp;

    public StateSpaceController(int nIn, int nOut, int nStates, StateSpaceGains ssg, double period) {
        //super(period);
        numIn = nIn;
        numOut = nOut;
        numStates = nStates;
        gains = ssg;
        
        //initialiaze matrices
        A = new Matrix(numStates, numStates);
        B = new Matrix(numStates, numOut);
        C = new Matrix(numOut, numStates);
        D = new Matrix(numOut, numOut);
        L = new Matrix(numStates, numOut);
        K = new Matrix(numOut, numStates);
        X = new Matrix(numStates, 1);
        R = new Matrix(numStates, 1);
        Y = new Matrix(numOut,numStates);
        X_hat = new Matrix(numStates, 1);
        U = new Matrix(numOut, 1);
        U_max = new Matrix(numOut, 1);
        U_min = new Matrix(numOut, 1);
        U_temp = new Matrix(numStates, 1);
        b_u = new Matrix(numStates, 1);
        l_y = new Matrix(numStates, 1);
        l_c = new Matrix(numStates, numStates);
        a_lc = new Matrix(numStates, numStates);
        alc_xhat = new Matrix(numStates, 1);
        xhatp1 = new Matrix(numStates, 1);
    }
    
    public void updateGains() {
        X_hat = U_temp.clone();
        X_hat.subtractMatrix(R);
        U_temp = U.clone();
        U_temp.multiplyMatrix(K);

        for(int i=0; i < numOut; i++) {
            double u_i = U.getValue(0,i)+i;
            double u_max = U_max.getValue(0, i);
            double u_min = U_min.getValue(0, i);
            if (u_i > u_max) {
                u_i = u_max;
            } else if (u_i < u_min) {
                u_i = u_min;
            }
        }
        Matrix b_u = B.clone();
        b_u.multiplyMatrix(U);
        Matrix l_y = L.clone();
        l_y.multiplyMatrix(Y);
        Matrix l_c = L.clone();
        l_c.multiplyMatrix(C);
        Matrix a_lc = (A.clone());
        a_lc.subtractMatrix(l_c);
        Matrix alc_xhat = a_lc.clone();
        alc_xhat.multiplyMatrix(X_hat);
        l_y = xhatp1.clone();
        l_y.addMatrix(alc_xhat);
        b_u = X_hat.clone();
        b_u.addMatrix(xhatp1);
    }
    
}