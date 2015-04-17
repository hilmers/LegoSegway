package hardware;

import lejos.robotics.RegulatedMotor;

public class SegwayMotor {
	private RegulatedMotor motor;
	
	public SegwayMotor(RegulatedMotor motor) {
		this.motor = motor;
	}
	
	public void rotateForward(int speed) {
		motor.setSpeed(speed);
		motor.forward();
	}
	
	public void rotateBackward(int speed) {
		motor.setSpeed(speed);
		motor.backward();
	}
}
