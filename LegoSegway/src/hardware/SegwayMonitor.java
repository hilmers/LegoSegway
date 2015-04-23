package hardware;

public class SegwayMonitor {
	private int speed;
	private double position;
	private double velocity;
	private float angle;
	private float angVel;
	
	public synchronized int getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public synchronized boolean forward() {
		return angVel > 0;
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
	
	public synchronized float getAngle() {
		return angle;
	}
	
	public synchronized void setAngle(float angle) {
		this.angle = angle;
	}
	
	public synchronized float getAngularVelocity() {
		return angVel;
	}
	
	public synchronized void setAngularVelocity(float angVel) {
		this.angVel = angVel;
	}
}
