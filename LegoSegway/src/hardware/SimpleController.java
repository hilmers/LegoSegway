package hardware;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class SimpleController implements Runnable {
	private GyroSensor gyro;
	private SegwayMotor left, right;
	private long samplInterv;

	public SimpleController(GyroSensor gyro, SegwayMotor left,
			SegwayMotor right, long sampInterv) {
		this.gyro = gyro;
		this.left = left;
		this.right = right;
		this.samplInterv = sampInterv;
	}

	public void run() {
		float sensorValue;
		while (!Thread.interrupted()) {
			sensorValue = gyro.getSensorValue();
			
			if (sensorValue < 0) {
				left.rotateBackward(10);
				right.rotateBackward(10);
			} 
			
			else if (sensorValue > 0) {
				left.rotateForward(10);
				right.rotateForward(10);
				
			}
			
			
			Delay.msDelay(samplInterv);
			
			

		}

	}

	public static void main(String[] args) {
		RegulatedMotor motor_left = new EV3LargeRegulatedMotor(MotorPort.C);
		RegulatedMotor motor_right = new EV3LargeRegulatedMotor(MotorPort.B);
		SegwayMotor left = new SegwayMotor(motor_left);
		SegwayMotor right = new SegwayMotor(motor_right);
		GyroSensor gyro = new GyroSensor(0.6f, 100);
		SimpleController controller = new SimpleController(gyro, left, right,
				100);
		Thread thread = new Thread(controller);
		thread.start();

	}

}
