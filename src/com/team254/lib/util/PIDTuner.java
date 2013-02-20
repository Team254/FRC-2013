package com.team254.lib.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 * TCP witchcraft.
 * cRIO JVM no gusta UDP.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class PIDTuner {
  private static PIDTuner instance = null;
  private final int PORT_NUMBER = 41234; // ?
  
  public static PIDTuner getInstance() {
    if (instance == null) {
      instance = new PIDTuner();
    }
    return instance;
  }
  
  public void pushData(double setpoint, double value, double control) {
    ServerSocketConnection socket = null;
        
    /*
    try {
      String address = "socket://" + HOST_ADDRESS + ":" + PORT_NUMBER;
      String message = "Stuff: " + setpoint + "," + value + "," + control;
      
      System.out.println("Establishing connection...");
      socket = (ServerSocketConnection) Connector.open(address);
      socketConnect = (SocketConnection) socket.acceptAndOpen();
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
    */
    
    try {
      socket = (ServerSocketConnection) Connector.open("serversocket://:" + PORT_NUMBER); 
      while(true) { 
        SocketConnection socketConnect = (SocketConnection) socket.acceptAndOpen(); 
        Connection c = new Connection(socketConnect); 
        c.start(); 
      } 
    } catch (IOException e) {
       System.out.println("ERROR: " + e.getMessage());
    }
  }
  
  private class Connection extends Thread {
    private SocketConnection client;
    
    public Connection(SocketConnection client) {
      this.client = client;
    }
    
    public void run() {
      PrintStream out = null;
      OutputStream output = null;
      try {
        out = new PrintStream(client.openOutputStream());
        output = client.openOutputStream();
        String message = "Call me maybe";
        
        // Writing methpo
        out.println(message);
        
        output.write(message.getBytes());
        output.flush();
        
      } catch(Throwable ioe) {
        System.out.println(ioe.getMessage());
      } finally {
        try {
          if(out != null) {
            out.close();
          }
          if(client != null) {
            client.close();
          }
        } catch(IOException ioe){
          System.out.println(ioe.getMessage());
        }
      }
    }
  }
}