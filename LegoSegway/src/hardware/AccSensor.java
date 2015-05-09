package hardware;

import java.util.ArrayList;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicAccelerometer;
import lejos.hardware.sensor.SensorMode;

public class AccSensor {
	private float sample[] = new float[3];
	private double angle;
	private ArrayList<Double> list;

	HiTechnicAccelerometer acc;

	public AccSensor() {
		acc = new HiTechnicAccelerometer(SensorPort.S3);
		list = new ArrayList<Double>();
	}

	public float[] getAccData() {
		SensorMode mode = acc.getAccelerationMode();
		mode.fetchSample(sample, 0);
		// System.out.println("x " + sample[0]);
		// System.out.println("y " + sample[0]);
		// System.out.println("z " + sample[2]);

		float x = sample[0];
		float z = sample[2];

		// double accData = Math.toDegrees((Math.atan2(x, z) + Math.PI)) -
		// 272.5315164670967;
		/*
		 * list.add(accData);
		 * 
		 * if (list.size() > 20) { double sum = 0.0; for (int i = 0; i <
		 * list.size(); i++) { sum += list.get(i); }
		 * 
		 * System.out.println("sum " + sum / list.size()); } /* /*
		 * acc.fetchSample(sample, 0); double val = sample[0] / 9.81; if (val >
		 * 1) { val = 1.0; } else if (val < -1) { val = -1.0; } angle =
		 * Math.acos(val);
		 */
		return sample;
	}
}
