package hardware;

public class PID {
	private double beta, K, N, Td, Ti, Tr, Kp0, Ku, Tu = 0.0; //parameters
	private long h = 10;
	private double ad, bd = 0.0;
	private double I, D = 0.0;
	private double e, v, yOld = 0.0;
	private boolean integratorOn;
	public static final int INNER = 0, OUTER = 1;

	public PID(int type) {
		if (type == INNER) {
			//parameters in case controlling the inner loop
			
			// Ku 				ultimate gain / constant amplitude
			// Tu 				oscillation period
			Ku = 50000000.0;
			Tu = 20000.0; 			//20?
			
			
			beta = 1.0;
			integratorOn = true;
			//Kp0 = 50000000.0;
			//K = 0.6*Kp0; 
			K = 0.33 * Ku;
			Ti = 2.0 * K / Tu;
			Td = K*Tu/3.0;
			
			//Ti = 20.0 / 2.0; 
			Tr = 5.0;
			//Td = 20.0 / 8.0;
			N = 5;
		} else if(type == OUTER) {
			// Parameters in case controlling the outer loop
			beta = 1.0;
			integratorOn = true;
			
			//�ndrade till 10	
			K = 1000*0.0336;
			Ti = 100*0.2688;
			Tr = 5.0;
			Td = 100*0.000504;
			N = 5;
		}
		ad = Td / (Td + N*0.02); // H = 0.02
		bd = K*ad*N;
	}
	/**
	 * calculates the controller output in reference to the current input and the reference value
	 * @param y - the current value
	 * @param ref - the reference (desired) value
	 * @return the new output signal
	 */
	public synchronized double calculateOutput(double y, double ref) {
		e = ref - y;
		D = ad*D - bd*(y - yOld);
		yOld = y;
		v = K*(beta*ref - y) + I + D;
		return v;
	}

	/**
	 * Update the controller state. Uses tracking and anti-windup. 
	 * @param u - the calculated output
	 */
	public synchronized void updateState(double u) {
		if (integratorOn) {
			I = I + (K*h/Ti)*e + (h/Tr)*(u - v);
		} else {
			I = 0.0;
		}
		
	}

	/**
	 * Update the parameters in the controller
	 * @param beta
	 * @param K
	 * @param h
	 * @param N
	 * @param Td
	 * @param Ti
	 * @param Tr
	 */
	public synchronized void setParameters(double beta, double K, long h, double N, double Td, double Ti, double Tr) {
		this.beta = beta;
		this.K = K;
		this.h = h;
		this.N = N;
		this.Td = Td;
		this.Ti = Ti;
		this.Tr = Tr;
	}
	public synchronized long getSampleRate() {
		return h;
	}
	
	public void setIntegrator(boolean condition) {
		integratorOn = condition;
		I = 0;
	}

}
