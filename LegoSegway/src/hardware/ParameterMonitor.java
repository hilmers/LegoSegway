package hardware;

import java.util.ArrayList;

public class ParameterMonitor {
	private double Ku = 50000000.0, Tu = 20000.0;
	private boolean running = false;
	private ArrayList<Double> powerSample;
	private ArrayList<Double> angleSample;

	public synchronized void setTu(double Tu) {
		this.Tu = Tu;
	}

	public synchronized double getTu() {
		return Tu;
	}

	public synchronized void setKu(double Ku) {
		this.Ku = Ku;
	}

	public synchronized double getKu() {
		return Ku;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
		notifyAll();
	}

	public synchronized boolean isRunning() {
		return running;
	}

	public synchronized void waitForConnection() throws InterruptedException {
		while (!running) {
			wait();
		}
	}

	public void setPowerSample(ArrayList<Double> pS) {
		powerSample = pS;
	}

	public void setAngleSample(ArrayList<Double> aS) {
		angleSample = aS;
	}

	public ArrayList<Double> getPowerSample() {
		return powerSample;
	}

	public ArrayList<Double> getAngleSample() {
		return angleSample;
	}

}
