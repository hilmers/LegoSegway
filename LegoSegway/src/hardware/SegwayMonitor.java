package hardware;

public class SegwayMonitor {
	private int speed;
	private boolean forward;
	private double position;
	private double velocity;
	
	public synchronized int getSpeed() {
		return speed;
	}

	public synchronized boolean forward() {
		return forward;
	}
	
	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public synchronized void setForward(boolean forward) {
		this.forward = forward;
	}
	
	public synchronized double getPosition() {
		return position;
	}
	
	public synchronized void setPosition(double position) {
		this.position = position;
	}
	
	public synchronized double getVelocity() {
		return velocity;
	}

	public synchronized void setVelocity(double velocity) {
		this.velocity = velocity;
	}
}
