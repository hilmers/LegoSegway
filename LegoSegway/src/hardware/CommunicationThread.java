package hardware;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CommunicationThread implements Runnable {
	ServerSocket serverSocket;
	Socket client;
	BufferedReader is;
	PrintWriter os;
	ParameterMonitor mon;
	int ref;

	public CommunicationThread(int port, ParameterMonitor mon) {
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
			is = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			os = new PrintWriter(client.getOutputStream(), true);
			System.out.println("Connection established");
		} catch (IOException e1) {
			System.out.println("Could not connect");
		}
		while (true) {
			try {
				String message = is.readLine();
				message = message.trim();
				if (message.length() > 2) {
					if (message.startsWith("start")) {
						startRegul();
					} else if (message.startsWith("stop")) {
						stopRegul();
					} else {
						String s = message.substring(2);
						s = s.trim();
						double val = 0;
						try {
							val = Double.parseDouble(s);
						} catch (NumberFormatException e) {
							os.write("NaN, try again!\n");
							os.flush();
							continue;
						}
						if (message.startsWith("tu")) {
							mon.setTu(val);
							os.write("Tu changed to: " + val + "\n");
						} else if (message.startsWith("ku")) {
							mon.setKu(val);
							os.write("Ku changed to: " + val);
						} else {
							os.write("not a supported command\n");
						}
					}
				} else {
					os.write("not a supported command\n");
				}
				os.flush();
			} catch (IOException e) {

			}

		}

	}

	private void stopRegul() {
		mon.setRunning(false);
		FileWriter writer = null;
		try {
			writer = new FileWriter("powerSamples.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Double> powerSamples = mon.getPowerSample();
		double power;
		for (int i = 0; i < powerSamples.size(); i++) {
			power = powerSamples.get(i);

			try {
				writer.write(String.valueOf(power) + '\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FileWriter writer2 = null;
		try {
			writer2 = new FileWriter("angleSamples.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Double> angleSamples = mon.getAngleSample();
		double angle;
		for (int i = 0; i < angleSamples.size(); i++) {
			angle = angleSamples.get(i);

			try {
				writer2.write(String.valueOf(angle) + '\n');
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			writer2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void startRegul() {
		mon.setRunning(true);
	}

}