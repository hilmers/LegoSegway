package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.filter.LowPassFilter;
import lejos.utility.Delay;

public class GyroSensor {
	private HiTechnicGyro gyro;
	private long time, diff;
	private boolean timeChecked = false;
	private LowPassFilter filter;
	int sample;
	
	float thresh; // Gyroscope sensor threshold
	float sensorVal = 0;
	long sampInterv;
	float angle = 0;

	public GyroSensor() {
		gyro = new HiTechnicGyro(SensorPort.S2);
		filter = new LowPassFilter(gyro, 0.1f);
		sample = gyro.sampleSize();
		this.thresh = thresh;
		this.sampInterv = sampInterv;
	}


	public synchronized float angleVelocity() {
		int samples = 5;
		float[] sample = new float[1];

		float mean = 0;
		// Read some samples from the sensor and then
		// calculate the mean value of that.
		for (int i = 0; i < samples; i++) {
			gyro.getRateMode().fetchSample(sample, 0);
			// System.out.printf("velocity: %.2f\n", sample[0]);
			mean += sample[0];
		}

		mean = mean / samples;

		if (Math.abs(mean) > Math.abs(thresh)) {
			sensorVal = mean;
		} else {
			sensorVal = 0;
		}

		//System.out.println("Sensor-val: " + sensorVal);
		
		angle += sensorVal * 1/100;
		System.out.println("Angle: " + angle);
		
		return sensorVal;
	}
	
	public float getAngle() {
		if (!timeChecked) time = System.currentTimeMillis();
		diff = System.currentTimeMillis() - time;
		time += diff;
		angle += angleVelocity() * diff;
		return angle;
	}

}
