package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection {
	Socket clientSocket;
	DataInputStream is;
	DataOutputStream os;

	public ClientConnection(String host, int port) throws UnknownHostException, IOException {
		clientSocket = new Socket(host, port);
		
		is = new DataInputStream(clientSocket.getInputStream());
		os = new DataOutputStream(clientSocket.getOutputStream());
	}
	public void sendRef(int ref) {
		try {
			os.writeInt(ref);
		} catch (IOException e) {
			
		}
		
	}
	
	public void getValues(int cont, float angle) {
//		try {
//		
//		} catch (IOException e) {
//			
//		}
	}
	

}
