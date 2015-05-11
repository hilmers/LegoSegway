package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicationThread implements Runnable {
	DataInputStream is;
	Socket conn;
	Monitor mon;
	public CommunicationThread(Monitor mon) {
	try {	
		this.mon = mon;
		this.conn = new Socket("10.0.1.1", 1337);

	} catch (IOException e) {
		System.out.println("Server not initialized");
	}
	}
	@Override
	public void run() {
		try {
		is = new DataInputStream(conn.getInputStream());
			System.out.println("Connection established");
		} catch (IOException e1) {
			System.out.println("Could not connect");
		}
		// TODO Auto-generated method stub
		while (true) {
			try {
				mon.setCurrentAngle(is.readDouble());
				System.out.println("Read " + mon.getCurrentAngle());
				mon.setControlSignal(is.readDouble());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
