package client;

public class Monitor {
	private int controlSignal, currentAngle, referenceValue;

	public Monitor() {
		this.controlSignal = 0;
		this.currentAngle = 0;
		this.referenceValue = 0;
	}
	
	public int getControlSignal() {
		return controlSignal;
	}

	public synchronized void setControlSignal(int controlSignal) {
		this.controlSignal = controlSignal;
	}

	public int getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(int currentAngle) {
		this.currentAngle = currentAngle;
	}

	public int getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(int referenceValue) {
		this.referenceValue = referenceValue;
	}

}
