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
		double v = 0.0;
		double angle = 0.0;
		System.out.println("Running...");
	
		while (true) {	
			long start = System.currentTimeMillis();
			int power = 0;
			boolean forward = true;
			synchronized (angleController) {
				power = 0;
				v = 0.0;
				forward = true;
				angle = gyro.getAngle();
	
			
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
							mon.setV(-power);
						} else {
							angleController.updateState((v/100)*1000);
							mon.setV(power);
						}
						power = 99;
					}
				
				mon.setAngle(angle);
				//mon.setSpeed(power);
				
					
					if (forward) {
						segway.forward(power, power);
					} else {
						segway.backward(power, power);
						

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
