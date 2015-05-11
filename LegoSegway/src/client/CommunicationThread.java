package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationThread implements Runnable {
	DataInputStream is;
	Socket conn;
	Monitor mon;
	PrintWriter controlWriter, angleWriter;
	public CommunicationThread(Monitor mon) {
	this.mon = mon;
	}
	@Override
	public void run() {
		try {
			this.conn = new Socket("10.0.1.1", 1337);
			controlWriter = new PrintWriter("control_data.txt", "UTF-8");
			angleWriter = new PrintWriter("angle_data.txt", "UTF-8");
			is = new DataInputStream(conn.getInputStream());
			System.out.println("Connection established");
		} catch (IOException e1) {
			System.out.println("Could not connect");
		}
		// TODO Auto-generated method stub
		while (conn.isConnected()) {
			try {
			//	System.out.println("Trying to read");
				mon.setCurrentAngle(is.readDouble());
			//	System.out.println("Read " + mon.getCurrentAngle());
				mon.setControlSignal(is.readDouble());
			//	System.out.println("Cont: " + mon.getControlSignal());
				angleWriter.println(mon.getCurrentAngle());
				controlWriter.println(mon.getControlSignal());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				angleWriter.close();
				controlWriter.close();
			}
		}
	}

}
