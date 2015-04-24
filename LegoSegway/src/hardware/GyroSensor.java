package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.filter.LowPassFilter;

public class GyroSensor {
	private HiTechnicGyro gyro;
	private long time, diff;
	private boolean checkedTimeFirstTime = false;
	private LowPassFilter filter;
	private float offset = 0;
	private float[] sample;
	private double angle = 0;

	public GyroSensor(float sampleTime) {
		gyro = new HiTechnicGyro(SensorPort.S2);
		filter = new LowPassFilter(gyro, 0.1f);
		sample = new float[gyro.sampleSize()];
	}

	public synchronized double angleVelocity() {
		filter.fetchSample(sample, 0);

	
		int	sampleSize = gyro.sampleSize();
		offset = ((sampleSize - 1) * offset + (sample[0] / sampleSize));
	
		return sample[0] + offset;

	}

	public double getAngle() {
		if (!checkedTimeFirstTime) { 
			time = System.currentTimeMillis();
			checkedTimeFirstTime = true;
		}
		diff = System.currentTimeMillis() - time;
		time += diff;
		angle = angle + (angleVelocity() * diff);
		
		
		if ( angle < 0.5 && angle > -0.5){
			angle = 0.0;
		}
		
		return angle;

	}

}
