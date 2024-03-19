package com.coolteam.demos;

import java.io.*;
import lejos.hardware.Bluetooth;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.NXTCommConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.utility.Delay;

class DoBluetoothStuff implements Runnable {
  @Override
  public void run() {
    while (true) {
      InputStream is = BluetoothTest.connection.openInputStream();
      BufferedReader r = new BufferedReader(new InputStreamReader(is));
      OutputStream os = BluetoothTest.connection.openOutputStream();
      BufferedWriter w = new BufferedWriter(new OutputStreamWriter(os));

      try {
        byte[] b;
        for (int i = 0; i < 50; i++) {
          String n = r.readLine();
          System.out.println(n);
          b = (n + "\r\n").getBytes();
          BluetoothTest.connection.write(b, b.length);
          Thread.sleep(10);
          w.write(n);
          w.flush();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}

public class BluetoothTest {
  private boolean messageReceived = false;
  private boolean suppressed = false;
  static NXTCommConnector connector = Bluetooth.getNXTCommConnector();
  static NXTConnection connection =
      connector.waitForConnection(
          10000, BTConnection.RAW); // 1000ms time for your to connect to the brick

  public void checkConnection() {
    if (connection == null) {
      LCD.drawString("Connection Failed :(", 0, 5);
      Delay.msDelay(1000);
    } else {
      LCD.drawString("Connection Successfull", 0, 5);
    }
  }

  public BluetoothTest() {
    while (true) {
      checkConnection();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
