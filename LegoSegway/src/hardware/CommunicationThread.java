package hardware;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class CommunicationThread implements Runnable {
	ServerSocket serverSocket;
	Socket client;
	BufferedReader is;
	DataOutputStream os;
	SegwayMonitor mon;
	int ref;

	public CommunicationThread(int port, SegwayMonitor mon) {
		try {
			this.mon = mon;
			serverSocket = new ServerSocket(port);

		} catch (IOException e) {
			System.out.println("Server not initialized");
		}

	}
	@Override
	public void run() {
		System.out.println("Waiting for connection...");
		try {
			client = serverSocket.accept();
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//os = new PrintWriter(client.getOutputStream(), true);
			os = new DataOutputStream(client.getOutputStream());
			System.out.println("Connection established");
		} catch (IOException e1) {
			System.out.println("Could not connect");
		}
		while (true) {
			try {
				os.writeDouble(mon.getAngle());
				os.writeDouble(mon.getV());
				Thread.sleep(100);
//				String message = is.readLine();
//				message = message.trim();
//				if (message.length() > 2) {
//					if (message.startsWith("start")) {
//						startRegul();
//					} else if (message.startsWith("stop")) {
//						stopRegul();
//					} else {
//						String s = message.substring(2);
//						s = s.trim();
//						double val = 0;
//						try {
//							val = Double.parseDouble(s);
//						} catch (NumberFormatException e) {
//							os.write("NaN, try again!\n");
//							os.flush();
//							continue;
//						}
//						if (message.startsWith("tu")) {
//							mon.setTu(val);
//							os.write("Tu changed to: " + val + "\n");
//						} else if (message.startsWith("ku")) {
//							mon.setKu(val);
//							os.write("Ku changed to: " + val);
//						} else {
//							os.write("not a supported command\n");
//						}
//					}
//				} else {
//					os.write("not a supported command\n");
//				}
//				os.flush();
			} catch (IOException e) {

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}
//	private void stopRegul() {
//		mon.setRunning(false);
//	}
//	private void startRegul() {
//		mon.setRunning(true);
//	}

}