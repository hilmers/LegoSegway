package hardware;

import regul.PID;

public class Regul implements Runnable {
	private Segway segway;
	private SegwayMonitor mon;
	private long h;
	private PID posController;
	private PID angleController;

	public Regul(Segway segway, SegwayMonitor mon, long h) {
		this.segway = segway;
		this.mon = mon;
		this.h = h;
		posController = new PID(PID.OUTER);
		angleController = new PID(PID.INNER);
	}

	@Override
	public void run() {
		double position = 0.0;
		double u = 0.0;
		double v = 0.0;
		double angle = 0.0;
		while (true) {
			long start = System.currentTimeMillis();

			synchronized(posController) {
				position = mon.getPosition();
				u = posController.calculateOutput(position, 0.0);
				posController.updateState(u);

				synchronized(angleController) {
					angle = mon.getAngle();
					v = angleController.calculateOutput(angle, u);
					System.out.println("signal from Controller: " + v);
					angleController.updateState(v);
					mon.setSpeed((int)Math.round(v));
					if (mon.forward()) {
						segway.forward(limit(mon.getSpeed()/2), limit(mon.getSpeed()/2));
					} else {
						segway.backward(limit(mon.getSpeed()/2), limit(mon.getSpeed()/2));
					}
				}

				long elapsed = System.currentTimeMillis() - start;
				if (elapsed < h) {
					try {
						Thread.sleep(h - elapsed);
					} catch (InterruptedException e) {
						System.out.println("controller was not able to sleep");
					}
				}
			}

		}
	}

	private int limit(int i) {
		return i > 100 ? 100 : i;
	}

}
