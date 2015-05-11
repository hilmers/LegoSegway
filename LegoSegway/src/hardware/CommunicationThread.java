package hardware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


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
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
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
		FileWriter writer = new FileWriter("powerSamples.txt");
		ArrayList<double> powerSamples = mon.getPowerSample();
		for (double power: powerSamples) {
			writer.write(String.valueOf(power));
		}
		writer.close();

		FileWriter writer2 = new FileWriter("powerSamples.txt");
		ArrayList<double> angleSamples = mon.getAngleSample();
		for (double angle: angleSamples) {
			writer2.write(String.valueOf(angle));
		}
		writer2.close();

	}
	private void startRegul() {
		mon.setRunning(true);
	}

}