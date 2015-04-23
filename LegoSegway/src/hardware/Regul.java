package hardware;

import regul.PID;

public class Regul implements Runnable {
	private Segway segway;
	private SegwayMonitor mon;
	private long h;
	private PID inner;
	private PID outer;

	public Regul(Segway segway, SegwayMonitor mon, long h) {
		this.segway = segway;
		this.mon = mon;
		this.h = h;
		inner = new PID(PID.INNER);
		outer = new PID(PID.OUTER);
	}

	@Override
	public void run() {
		while (true) {
			long start = System.currentTimeMillis();
			
			
			//cascade the two controllers
			
			
			
			
			
			
			
			
			
			int speed = mon.getSpeed();
			if (mon.forward()) {
				segway.forward(speed/2, speed/2);
			} else {
				segway.backward(speed/2, speed/2);
			}
			long elapsed = System.currentTimeMillis() - start;
			if (elapsed < h) {
				try {
					Thread.sleep(h - elapsed);
				} catch (InterruptedException e) {
					System.out.println("was not able to sleep");
				}
			}

		}
	}

}
