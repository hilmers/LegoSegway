package hardware;


public class CommunicationThread implements Runnable {

	Connection conn;
	SegwayMonitor mon;
	int ref;
	
	public CommunicationThread(Connection conn, SegwayMonitor mon) {
		this.conn = conn;
		this.mon = mon;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			conn.sendValues(mon.getSpeed(), mon.getAngle(), mon.getAngularVelocity(), mon.getPosition(), mon.getVelocity());
			ref = conn.readRef();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}