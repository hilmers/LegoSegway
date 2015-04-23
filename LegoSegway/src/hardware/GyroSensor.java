package hardware;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.filter.LowPassFilter;

public class GyroSensor {
	private HiTechnicGyro gyro;
	private long time, diff;
	private boolean timeChecked = false;
	private LowPassFilter filter;
	private float offset = 0;
	float[] sample;
	float angle = 0;
	public double EMAOFFSET = 0.0005;
	
	
	float static_offset;

	public GyroSensor(float sampleTime) {
		gyro = new HiTechnicGyro(SensorPort.S2);
		filter = new LowPassFilter(gyro, 0.1f);
		sample = new float[gyro.sampleSize()];
		//Test att beräkna statisk offset
		filter.fetchSample(sample, 0);
		float sum = 0;
		for (float samp : sample)
			sum += samp;
		
		static_offset = sum / gyro.sampleSize();
		
	}

	public synchronized float angleVelocity() {
		filter.fetchSample(sample, 0);
		//offset = (float) (EMAOFFSET * sample[0] + (1 - EMAOFFSET) * offset);
		//System.out.println(offset);
		int	a = gyro.sampleSize();
		int k = a-1;
		offset = (float) (k * offset + (sample[0] / a));
		System.out.println("stat offs " + static_offset);
		System.out.println("offs " + offset);
		System.out.println("samp " + sample[0]);
		return sample[0] + offset - static_offset;
	}

	public float getAngle() {
		if (!timeChecked) { 
			time = System.currentTimeMillis();
			timeChecked = true;
		}
		diff = System.currentTimeMillis() - time;
		time = time + diff;
		angle = angle + (angleVelocity() * 0.1f);
		System.out.println("angle " + angle);
		return angle;

	}

}
