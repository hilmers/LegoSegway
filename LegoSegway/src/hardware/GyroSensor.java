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
		filter = new LowPassFilter(gyro, 0.1f);
		sample = new float[gyro.sampleSize()];
	}

	public synchronized double angleVelocity() {
		filter.fetchSample(sample, 0);
	
		int	sampleSize = gyro.sampleSize();
		
		//offset = ((sampleSize - 1) * offset + (sample[0] / sampleSize));
		drift += sample[0] + 0.3;
		nbrOfSamples++;
		offset = (double) drift / nbrOfSamples;
	
		
		//System.out.println(sample[0] + offset);
		return sample[0] + offset;
	}

	public double getAngle() {
		if (!checkedTimeFirstTime) { 
			time = System.currentTimeMillis();
			checkedTimeFirstTime = true;
		}
		diff = System.currentTimeMillis() - time;
		time += diff;
		angle = angle + (angleVelocity() * ((double) diff/1000));
		//System.out.println("diff " + diff);
		
		if (angle > -0.05 && angle < 0.05) angle = 0;
		
		return angle;

	}

}
