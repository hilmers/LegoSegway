package hardware;

public class CompFilter {
	private double ygs, yas, yf, h, eh, dt, angle = 0.0;
	private static double OFFSET = 270;
	private AccSensor accSensor;

	// h is the sample-time
	public CompFilter(double h) {
		accSensor = new AccSensor();
		this.h = h / 1000;
		dt = this.h;
	}

	public double compFilt(double ya) {

		float angularVelocity = (float) ya;
		float gyroMovement = (float) (angularVelocity * dt);

		float[] accData = accSensor.getAccData();
		double accX = accData[0];
		double accZ = accData[2];

		double accY = Math.toDegrees((Math.atan2(accX, accZ) + Math.PI))
				- OFFSET;
		angle = (0.9 * (angle + (angularVelocity * dt)) + 0.1 * accY);

		return angle;
	}
}
