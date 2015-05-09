package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.filter.LowPassFilter;

public class GyroSensor {
	private HiTechnicGyro gyro;
	private long time, diff;
	private boolean checkedTimeFirstTime = false;
	private LowPassFilter filter;
	private double offset = 0;
	private float[] sample;
	private double angle = 0;
	private float drift = 0;
	private double nbrOfSamples = 0;

	public GyroSensor(float sampleTime) {
		gyro = new HiTechnicGyro(SensorPort.S2);
		filter = new LowPassFilter(gyro, sampleTime);
		sample = new float[gyro.sampleSize()];
	}

	public synchronized double angleVelocity() {
		filter.fetchSample(sample, 0);

		drift += sample[0] + 0.3;
		nbrOfSamples++;
		offset = (double) drift / nbrOfSamples;

		double samp = sample[0] + offset;
		/*
		 * if (samp > -5 && samp < 5) { samp = 0; }
		 */

		return samp;
	}

	public double getAngle() {

		if (!checkedTimeFirstTime) {
			time = System.currentTimeMillis();
			checkedTimeFirstTime = true;
		}

		diff = System.currentTimeMillis() - time;
		time += diff;
		angle = angle + (angleVelocity() * ((double) diff / 1000));

		/*
		 * if (angle > -5 && angle < 5) { angle = 0; }
		 */

		return angle;

	}

}
