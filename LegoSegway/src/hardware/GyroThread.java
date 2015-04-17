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
			mon.setForward(angVel < 0);
			mon.setSpeed((Math.round(angVel*10)));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
