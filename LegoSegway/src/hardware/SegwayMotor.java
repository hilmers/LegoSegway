package hardware;

import lejos.robotics.EncoderMotor;

public class SegwayMotor {
	private EncoderMotor motor;
	
	public SegwayMotor(EncoderMotor motor) {
		this.motor = motor;
	}
	
	public void rotateForward(int speed) {
		motor.stop();
		motor.setPower(speed);
		motor.forward();
	}
	
	public void rotateBackward(int speed) {
		motor.stop();
		motor.setPower(speed);
		motor.backward();
	}
	
	public int getTachoCount() {
		return motor.getTachoCount();
	}
}
