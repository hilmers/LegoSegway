package hardware;

public class Regul implements Runnable {
	private Segway segway;
	private SegwayMonitor mon;
	private long h;
	private PID angleController;
	private GyroSensor gyro;
	private ParameterMonitor parmon;

	public Regul(Segway segway, SegwayMonitor mon, ParameterMonitor parmon,
			long h) {
		this.segway = segway;
		this.mon = mon;
		this.parmon = parmon;
		this.h = h;
		gyro = segway.getGyro();
		angleController = new PID(PID.INNER);
	}

	@Override
	public void run() {
		double ref =-1.0;
		double u = 0.0;
		double v = 0.0;
		double angle = 0.0;
		double lastAngle = 0.0;
		double angularVel = 0.0;
		System.out.println("Running...");
		int tmp = 0;
		while (true) {	
			long start = System.currentTimeMillis();
			int power = 0;
			boolean controlled = false;
			boolean forward = true;
			synchronized (angleController) {
				power = 0;
				v = 0.0;
				forward = true;
				//controlled = false;
				// Calculate control signal
				angle = gyro.getAngle();
			//if (angle < ref + 1.5 && angle > ref - 1.5) {
					//angle = ref;
					//gyro.setAngle(ref);
				//}
			
					v = angleController.calculateOutput(angle, ref);
					
					//controlled = true;
					if (v < 0) {
						
						v = -	1.3*v;
					} else {
						forward = false;
						
					}
					//System.out.println(v);
					v = (v  / 1000)* 100;
					power = (int) v;
					if (power > 99) {
						if (forward) {
							angleController.updateState(-(v/100)*1000);
							
						} else {
							angleController.updateState((v/100)*1000);
						}
						power = 99;
					}
				
				// System.out.println("controller out: " + v);
				mon.setAngle(angle);
				//mon.setAngularVelocity(angularVel);
				// mon.setSpeed((int) Math.round(v));
				mon.setSpeed(power);
				if (true) {
					
					if (forward) {
						segway.forward(power, power);
					} else {
						segway.backward(power, power);
						

					}
				}


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

	}

}
