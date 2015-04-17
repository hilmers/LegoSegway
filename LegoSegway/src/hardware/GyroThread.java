package hardware;

public class GyroThread implements Runnable {
	private GyroSensor sensor;
	private MotorMonitor mon;
	
	public GyroThread(GyroSensor sensor, MotorMonitor mon) {
		this.sensor = sensor;
		this.mon = mon;
	}
	
	
	@Override
	public void run() {
		while (true) {
			float angVel = sensor.getSensorValue();
			mon.setForward(angVel > 0);
			mon.setDegrees(1);
		}
	}

}
