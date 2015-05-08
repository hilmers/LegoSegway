package hardware;

public class ParameterMonitor {
	private double Ku = 50000000.0, Tu = 20000.0;
	private boolean connected = false;
	
	public synchronized void setTu(double Tu) {
		this.Tu = Tu;
	}
	
	public synchronized double getTu() {
		return Tu;
	}
	
	public synchronized void setKu(double Ku) {
		this.Ku = Ku;
	}
	
	public synchronized double getKu() {
		return Ku;
	}
	
	public synchronized void setConnected(boolean connected) {
		this.connected = connected;
		notifyAll();
	}
	
	public synchronized boolean isConnected() {
		return connected;
	}

	public synchronized void waitForConnection() throws InterruptedException {
		while(!connected) {
			wait();
		}
	}
	

}
