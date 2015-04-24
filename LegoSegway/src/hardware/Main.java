package hardware;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;

public class Main {
	public static void main(String[] args) {
		SegwayMotor left = new SegwayMotor(new EV3LargeRegulatedMotor(MotorPort.C));
		SegwayMotor right = new SegwayMotor(new EV3LargeRegulatedMotor(MotorPort.B));
		GyroSensor gyro = new GyroSensor(0.1f);
		Segway segway = new Segway(left, right, gyro);
		SegwayMonitor mon = new SegwayMonitor();
		Thread gyro_thread = new Thread(new GyroThread(gyro, mon));
		Thread pos_thread = new Thread(new PositionThread(segway, mon, 20));
		Thread regul = new Thread(new Regul(segway, mon, 20	)); // H = 0.1s
		gyro_thread.start();
		pos_thread.start();
		regul.start();
	}
}
