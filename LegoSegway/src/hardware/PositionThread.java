package hardware;

public class PositionThread implements Runnable {
	private Segway segway;
	private SegwayMonitor mon;
	private long h;

	public PositionThread(Segway segway, SegwayMonitor mon, long h) {
		this.segway = segway;
		this.mon = mon;
		this.h = h;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		double position = segway.getPosition();
		try {
			double velocity = segway.getVelocity();
			mon.setVelocity(velocity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		mon.setRelativePosition(position);
		long elapsed = System.currentTimeMillis() - start;
		if (elapsed < h) {
			try {
				Thread.sleep(h - elapsed);
			} catch (InterruptedException e) {
				System.out.println("position sampler could not sleep");
			}
		}
	}

}
