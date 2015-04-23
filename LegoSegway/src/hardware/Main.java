package hardware;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {
	public static void main(String[] args) {
		SegwayMotor left = new SegwayMotor(new EV3LargeRegulatedMotor(MotorPort.C));
		SegwayMotor right = new SegwayMotor(new EV3LargeRegulatedMotor(MotorPort.B));
		GyroSensor gyro = new GyroSensor(0.6f, 100);
		Segway segway = new Segway(left, right, gyro);
		MotorMonitor mon = new MotorMonitor();
		Thread gyro_thread = new Thread(new GyroThread(gyro, mon));
		Thread motorThread = new Thread(new MotorThread(segway, mon));
		gyro_thread.start();
		motorThread.start();
		
	}
}
