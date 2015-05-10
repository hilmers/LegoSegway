package hardware;

public class Regul implements Runnable {
	private Segway segway;
	private SegwayMonitor mon;
	private long h;
	private PID posController;
	private PID angleController;
	private GyroSensor gyro;
	private ParameterMonitor parmon;
	// private AccSensor accSensor;
	private CompFilter compFilter;

	public Regul(Segway segway, SegwayMonitor mon, ParameterMonitor parmon,
			long h) {
		this.segway = segway;
		this.mon = mon;
		this.parmon = parmon;
		this.h = h;
		gyro = segway.getGyro();
		posController = new PID(PID.OUTER);
		angleController = new PID(PID.INNER);
		compFilter = new CompFilter((double) h);
		// accSensor = new AccSensor();
	}

	@Override
	public void run() {
		double position = 0.0;
		double u = 0.0;
		double v = 0.0;
		double angle = 0.0;
		double angularVel = 0.0;
		System.out.println("Running...");
		// try {
		// parmon.waitForConnection();
		// } catch (InterruptedException e1) {
		//
		// }
		while (true) {
			long start = System.currentTimeMillis();

			// synchronized(posController) {
			// Calculate control signal
			// position = mon.getPosition();
			// u = posController.calculateOutput(position, 0.0);

			// Update state
			// posController.updateState(u);
			int power = 0;
			boolean controlled = false;
			synchronized (angleController) {
				power = 0;
				controlled = false;
				// Calculate control signal
				// angle = mon.getAngle() + mon.getAngularVelocity();
				angularVel = gyro.angleVelocity();
				// double gyroAngle = gyro.getAngle() + angularVel;
				// double accAngle = accSensor.getAccData();
				angle = compFilter.compFilt(angularVel);
				if (angle < 2 && angle > 0) {
					angle = 0;
				}
				v = 0.0;
				// System.out.println("angle " + angle);
				if (angle != 0.0) {
					v = angleController.calculateOutput(angle, 0.0);
					controlled = true;
					power = (int) Math.abs(v);
					power = 55 + (power * 45) / 100;
				}

				// System.out.println("controller out: " + v);
				mon.setAngle(angle);
				mon.setAngularVelocity(angularVel);
				// mon.setSpeed((int) Math.round(v));
				mon.setSpeed(power);
				if (controlled) {
					if (mon.forward()) {
						segway.forward(limit(mon.getSpeed()),
								limit(mon.getSpeed()));
					} else {
						segway.backward(limit(mon.getSpeed()),
								limit(mon.getSpeed()));

					}
				}

				if (v > 100 || v < -100) {
					angleController.setIntegrator(false);

				} else {
					angleController.setIntegrator(true);
				}
				angleController.updateState(v);

				// This updates the controller parameters
				// If they have not changed, the old value will be used
				// angleController.updateParameters(parmon.getKu(),
				// parmon.getTu());

				// Run motor

			}

			long elapsed = System.currentTimeMillis() - start;
			if (elapsed < h) {
				try {
					Thread.sleep(h - elapsed);
				} catch (InterruptedException e) {
					System.out.println("controller was not able to sleep");
				}
			}
			//segway.stop();
		}

		// }
	}

	private int limit(int j) {

		if (j > 100) {
			return 100;
		} else if (j < -100) {
			return -100;

		}
		return j;

	}
}
