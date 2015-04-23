package hardware;

public class SegwayMonitor {
	private int speed;
	private double position;
	private double velocity;
	private double angle;
	private double angVel;
	
	public synchronized int getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public synchronized boolean forward() {
		return angVel < 0;
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
	
	public synchronized double getAngle() {
		return angle;
	}
	
	public synchronized void setAngle(double angle) {
		this.angle = angle;
	}
	
	public synchronized double getAngularVelocity() {
		return angVel;
	}
	
	public synchronized void setAngularVelocity(double angVel) {
		this.angVel = angVel;
	}
}
