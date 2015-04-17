package hardware;

public class MotorThread implements Runnable {
	private SegwayMotor motor;
	private MotorMonitor mon;
	
	public MotorThread(SegwayMotor motor, MotorMonitor mon) {
		this.motor = motor;
		this.mon = mon;
	}
	
	
	@Override
	public void run() {
		while (true) {
			int degrees;
			try {
				degrees = mon.getDegrees();
				if (mon.forward()) {
					motor.rotateForward(degrees);
				} else {
					motor.rotateBackward(degrees);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
