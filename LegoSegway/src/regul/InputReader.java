package regul;

public class InputReader implements Runnable {
	// private GYRO
	private InputMonitor mon;
	private long h;
	
	public InputReader(InputMonitor mon /*, the gyro-sensor to read value from, sampling-period */) {
		this.mon = mon;
		h = 50;
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			long start = System.currentTimeMillis();
			float y = 0;  //replace with read angular velocity
			mon.setAngularVelocity(y); 
			long elapsed = System.currentTimeMillis() - start;
			if (elapsed < h) {
				try {
					Thread.sleep(h - elapsed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

}
