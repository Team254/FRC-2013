package com.team254.lib.util;

import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 * TCP witchcraft.
 * cRIO JVM no gusta UDP.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class PIDTuner {
  private static PIDTuner instance = null;
  private final String HOST_ADDRESS = "10.2.54.125"; // ?
  private final int PORT_NUMBER = 41234; // ?
  
  public static PIDTuner getInstance() {
    if (instance == null) {
      instance = new PIDTuner();
    }
    return instance;
  }
  
  public boolean pushData(double setpoint, double value, double control) {
    SocketConnection socketConnect = null;
    OutputStream output = null;
    boolean successful = true;

    try {
      String address = "socket://" + HOST_ADDRESS + ":" + PORT_NUMBER;
      String message = "Stuff: " + setpoint + "," + value + "," + control;
      System.out.println("Establishing connection...");
      socketConnect = (SocketConnection) Connector.open(address);
      System.out.print("OK\nOpening output stream...");
      output = socketConnect.openOutputStream();
      System.out.print("OK\nWriting to output stream...");
      output.write(message.getBytes());
      output.flush();
      System.out.print("OK");
    } catch (IOException ex) {
      successful = false;
      ex.printStackTrace();
    } finally {
      try {
        System.out.println("Closing connections...");
        output.close();
        socketConnect.close();
        System.out.print("OK");
      } catch (IOException ex) {
        successful = false;
        ex.printStackTrace();
      }
    }
    return successful;
  }
}