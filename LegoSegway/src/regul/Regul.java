package regul;

public class Regul implements Runnable {
	private PID controller;
	private InputMonitor mon;
	private double ref = 0;
	
	public Regul(PID controller, InputMonitor mon) {
		this.controller = controller;
		this.mon = mon;
	}
	

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			long start = System.currentTimeMillis();
			float y = mon.getAngularVelocity();
			double u = controller.calculateOutput(y, ref);
			controller.updateState(u);
			
			//Transfer u and send it to motors
			
			long elapsed = System.currentTimeMillis() - start;
			
			if (elapsed < controller.getSampleRate()) {
				try {
					Thread.sleep(controller.getSampleRate() - elapsed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
