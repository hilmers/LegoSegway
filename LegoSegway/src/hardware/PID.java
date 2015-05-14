package hardware;

public class PID {
	private double K, N, Td, Ti, Tr; // parameters
	private double h = 0.025;
	private double ad, bd = 0.0;
	private double I, D = 0.0;
	private double e, v, yOld = 0.0;
	private boolean integratorOn = true;
	public static final int INNER = 0, OUTER = 1;

	public PID(int type) {
	
	
			this.integratorOn = true;
			K = 15.0; //15 // 10.0
			 Ti = 0.5;// 0.05 / alt 0.25
			Td = 6.0; //6.0
			 N = 18.0; //18
			 Tr = 10.0; //10

			 ad = Td / (Td + N * h); // H = 0.025
			 bd = K * ad * N;
	}

	/**
	 * calculates the controller output in reference to the current input and
	 * the reference value
	 * 
	 * @param y
	 *            - the current value
	 * @param ref
	 *            - the reference (desired) value
	 * @return the new output signal
	 */
	public synchronized double calculateOutput(double y, double ref) {
		e = ref - y;
		D = ad * D - bd * (y - yOld);
		yOld = y;
		
		v = K * e + I + D;
		
		return v;
	}

	/**
	 * Update the controller state. Uses tracking and anti-windup.
	 * 
	 * @param u
	 *            - the calculated output
	 */
	public synchronized void updateState(double u) {
		if (integratorOn) {
			
			I = I + (K * h / Ti) * e + (h / Tr) * (u - v);
		} else {
			I = 0.0;
		}

	}

	/**
	 * Update the parameters in the controller
	 * 
	 * @param beta
	 * @param K
	 * @param h
	 * @param N
	 * @param Td
	 * @param Ti
	 * @param Tr
	 */
	public synchronized void setParameters( double K, long h,
			double N, double Td, double Ti, double Tr) {
		this.K = K;
		this.h = h;
		this.N = N;
		this.Td = Td;
		this.Ti = Ti;
		this.Tr = Tr;
	}

	public synchronized double getSampleRate() {
		return h;
	}

	public void setIntegrator(boolean condition) {
		integratorOn = condition;
		I = 0;
	}


}
