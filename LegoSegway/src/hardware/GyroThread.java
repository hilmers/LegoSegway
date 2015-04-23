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
			mon.setSpeed((Math.round(angVel*10)));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
