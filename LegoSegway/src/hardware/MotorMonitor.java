package hardware;

public class MotorMonitor {
	private int speed;
	private boolean forward;
	private boolean hasChanged;
	
	public synchronized int getSpeed() throws InterruptedException {
		while(!hasChanged) {
			wait();
		}
		hasChanged = false;
		return speed;
	}

	public synchronized boolean forward() {
		return forward;
	}
	
	public synchronized void setSpeed(int speed) {
		this.speed = speed;
		hasChanged = true;
		notifyAll();
	}
	
	public synchronized void setForward(boolean forward) {
		this.forward = forward;
	}

}
