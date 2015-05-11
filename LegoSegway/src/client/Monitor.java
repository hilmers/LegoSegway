package client;

public class Monitor {
	private double controlSignal, currentAngle, referenceValue;

	public Monitor() {
		this.controlSignal = 0;
		this.currentAngle = 0;
		this.referenceValue = 0;
	}
	
	public double getControlSignal() {
		return controlSignal;
	}

	public synchronized void setControlSignal(double controlSignal) {
		this.controlSignal = controlSignal;
	}

	public double getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}

	public double getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(double referenceValue) {
		this.referenceValue = referenceValue;
	}

}
