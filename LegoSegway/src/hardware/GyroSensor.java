package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.filter.LowPassFilter;

public class GyroSensor {
	private HiTechnicGyro gyro;
	private long time, diff;
	private boolean checkedTimeFirstTime = false;
	private LowPassFilter filter;
	private float[] sample;
	private double angle = 0;
	
	public GyroSensor(float sampleTime) {
		gyro = new HiTechnicGyro(SensorPort.S2);
		filter = new LowPassFilter(gyro, sampleTime);
		sample = new float[gyro.sampleSize()];
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public synchronized double angleVelocity() {
		filter.fetchSample(sample, 0);
		return (double) sample[0] + 0.57; //offset
		
	}

	public double getAngle() {

		if (!checkedTimeFirstTime) {
			time = System.currentTimeMillis();
			checkedTimeFirstTime = true;
		}

		diff = System.currentTimeMillis() - time;
		time += diff;
		angle = angle + (angleVelocity() * ((double) diff / 1000));

		return angle;

	}

}
