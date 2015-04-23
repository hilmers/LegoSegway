package hardware;

public class GyroThread implements Runnable {
	private GyroSensor sensor;
	private SegwayMonitor mon;
	
	public GyroThread(GyroSensor sensor, SegwayMonitor mon) {
		this.sensor = sensor;
		this.mon = mon;
	}
	
	
	@Override
	public void run() {
		while (true) {
			float angVel = sensor.angleVelocity();
			mon.setAngularVelocity(angVel);
			float angle = sensor.getAngle();
			mon.setAngle(angle);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
