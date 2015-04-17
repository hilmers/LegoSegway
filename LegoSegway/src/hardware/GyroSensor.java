package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.utility.Delay;

public class GyroSensor {
	float thresh; // Gyroscope sensor threshold
	float sensorVal = 0;
	long sampInterv;

	public GyroSensor(float thresh, long sampInterv) {
		this.thresh = thresh;
		this.sampInterv = sampInterv;
	}

	private HiTechnicGyro gyro = new HiTechnicGyro(SensorPort.S2);

	public synchronized float getSensorValue() {
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

		System.out.println("Sensor-val: " + sensorVal);

		return sensorVal;
	}

}
