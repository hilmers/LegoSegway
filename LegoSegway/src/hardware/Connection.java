
package hardware;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
	ServerSocket serverSocket;
	Socket server;
	DataInputStream is;
	DataOutputStream os;

	public Connection(int port) {
		try {
			serverSocket = new ServerSocket(port);
			server = serverSocket.accept();
			
			is = new DataInputStream(server.getInputStream());
			os = new DataOutputStream(server.getOutputStream());
		} catch (IOException e) {
			
		}
	}
	
	public int readRef() {
		int ref = 0;
		try {
			ref = is.readInt();
		} catch (IOException e) {
			
		}
		
		return ref;
	}
	
	public void sendValues(int cont, double angle) {
		try {
			os.writeInt(cont);
			os.writeDouble(angle);
		} catch (IOException e) {
			
		}
	}
	

}
