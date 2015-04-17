package hardware;

public class MotorMonitor {
	private int degrees;
	private boolean forward;
	private boolean hasChanged;
	
	public synchronized int getDegrees() throws InterruptedException {
		while(!hasChanged) {
			wait();
		}
		hasChanged = false;
		return degrees;
	}

	public synchronized boolean forward() {
		return forward;
	}
	
	public synchronized void setDegrees(int degrees) {
		this.degrees = degrees;
		hasChanged = true;
		notifyAll();
	}
	
	public synchronized void setForward(boolean forward) {
		this.forward = forward;
	}

}
