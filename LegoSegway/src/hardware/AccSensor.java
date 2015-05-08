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
		angle = Math.acos(sample[0] / 9.81);
		return angle;
	}

}
