package hardware;

public class MotorThread implements Runnable {
	private Segway segway;
	private MotorMonitor mon;
	
	public MotorThread(Segway segway, MotorMonitor mon) {
		this.segway = segway;
		this.mon = mon;
	}
	
	
	@Override
	public void run() {
		while (true) {
			int speed;
			try {
				speed = mon.getSpeed();
				if (mon.forward()) {
					segway.forward(speed/2, speed/2);
				} else {
					segway.backward(speed/2, speed/2);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
