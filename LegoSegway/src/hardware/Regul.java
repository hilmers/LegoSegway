package hardware;


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
		System.out.println("Running...");


		while (true) {
			long start = System.currentTimeMillis();

			//	synchronized(posController) {
			//Calculate control signal
			//		position = mon.getPosition();
			//		u = posController.calculateOutput(position, 0.0);

			//Update state
			//		posController.updateState(u);

			synchronized(angleController) {
				//Calculate control signal
				angle = mon.getAngle() + mon.getAngularVelocity();
				v = angleController.calculateOutput(angle, u);

				//	System.out.println("angle: " + angle + " v: " + v);

				//Update state

				//System.out.println("angle: "+angle+"v : " + v);


				angleController.updateState(v);

				//Run motor
				mon.setSpeed((int)Math.round(v));
				if (mon.forward()) {
					segway.forward(limit(mon.getSpeed()), limit(mon.getSpeed()));
				} else {
					segway.backward(limit(mon.getSpeed()), limit(mon.getSpeed()));

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
			segway.stop();
		}

		//}
	}


	private int limit(int j) {
		int i = Math.abs(j);
		if (i > 100) {
			return 100;

		}
		return i;

	}
}
