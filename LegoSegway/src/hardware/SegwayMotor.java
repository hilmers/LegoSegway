package hardware;

import lejos.robotics.RegulatedMotor;

public class SegwayMotor {
	private RegulatedMotor motor;
	
	public SegwayMotor(RegulatedMotor motor) {
		this.motor = motor;
		motor.setSpeed(740);
	}
	
	public void rotateForward(int degrees) {
		motor.rotate(degrees);
	}
	
	public void rotateBackward(int degrees) {
		motor.rotate(-degrees);
	}
}
