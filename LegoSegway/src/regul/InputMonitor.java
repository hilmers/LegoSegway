package regul;

public class InputMonitor {
	private float angVel;
	
	public synchronized float getAngularVelocity() {
		return angVel;
	}
	
	public synchronized void setAngularVelocity(float angVel) {
		this.angVel = angVel;
	}
}
