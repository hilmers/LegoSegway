package hardware;

import lejos.robotics.RegulatedMotor;

public class SegwayMotor {
	private RegulatedMotor motor;
	
	public SegwayMotor(RegulatedMotor motor) {
		this.motor = motor;
		motor.setSpeed(740);
	}
	
	public void rotateForward(int degrees) {
		motor.stop();
		motor.forward();
	}
	
	public void rotateBackward(int degrees) {
		motor.stop();
		motor.backward();
	}
}
