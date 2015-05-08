package hardware;

public class ParameterMonitor {
	private double Tu = 0.0;
	private double Ku = 0.0;
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
	

}
