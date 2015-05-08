package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicAccelerometer;

public class AccSensor {
	private float sample[] = new float[3];
	private double angle;

	HiTechnicAccelerometer acc;

	public AccSensor() {
		acc = new HiTechnicAccelerometer(SensorPort.S3);
	}

	public double getAccData() {
		acc.fetchSample(sample, 0);
		double val = sample[0] / 9.81;
		if (val > 1) {
			val = 1.0;
		} else if (val < -1) {
			val = -1.0;
		}
		angle = Math.acos(val);
		return angle;
	}

}
