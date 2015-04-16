package tests;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.utility.Delay;

public class TestGyro implements Runnable {
	float thresh; // Gyroscope sensor threshold
	
	public TestGyro(float thresh) {
		this.thresh = thresh;
		
	}
	
	private HiTechnicGyro gyro = new HiTechnicGyro(SensorPort.S2);

	@Override
	public void run() {

		while (true) {
			int size = 5;
			float[] sample = new float[1];

			float mean = 0;
			for (int i = 0; i < size; i++) {
				gyro.getRateMode().fetchSample(sample, 0);
				System.out.printf("velocity: %.2f\n", sample[0]);
				mean += sample[0];
			}

		
			System.out.printf("Mean-val: %.2f\n", mean / size);
			Delay.msDelay(3000);
		}

	}

	public static void main(String[] args) {
		Thread thread = new Thread(new TestGyro((float) 0.5));
		thread.start();
	}

}
