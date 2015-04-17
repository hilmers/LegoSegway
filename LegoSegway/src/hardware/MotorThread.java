package hardware;

public class MotorThread implements Runnable {
	private SegwayMotor left;
	private SegwayMotor right;
	private MotorMonitor mon;
	
	public MotorThread(SegwayMotor left, SegwayMotor right, MotorMonitor mon) {
		this.left = left;
		this.right = right;
		this.mon = mon;
	}
	
	
	@Override
	public void run() {
		while (true) {
			int degrees;
			try {
				degrees = mon.getSpeed();
				if (mon.forward()) {
					left.rotateForward(degrees/2);
					right.rotateForward(degrees/2);
				} else {
					left.rotateBackward(degrees/2);
					right.rotateBackward(degrees/2);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
