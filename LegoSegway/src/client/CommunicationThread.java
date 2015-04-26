package client;

public class CommunicationThread implements Runnable {

	ClientConnection conn;
	Monitor mon;
	public CommunicationThread(ClientConnection conn, Monitor mon) {
		this.conn = conn;
		this.mon = mon;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
