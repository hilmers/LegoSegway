package hardware;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {
	public static void main(String[] args) {
		SegwayMotor left = new SegwayMotor(new UnregulatedMotor(MotorPort.C));
		SegwayMotor right = new SegwayMotor(new UnregulatedMotor(MotorPort.B));
		GyroSensor gyro = new GyroSensor(0.1f);
		Segway segway = new Segway(left, right, gyro);
		SegwayMonitor segmon = new SegwayMonitor();
		ParameterMonitor parmon = new ParameterMonitor();
//		Thread gyro_thread = new Thread(new GyroThread(gyro, mon));
//		Thread pos_thread = new Thread(new PositionThread(segway, mon, 10));
		Thread regul = new Thread(new Regul(segway, segmon, parmon, 10)); // H = 0.02s
		Thread com = new Thread(new CommunicationThread(1337, parmon));
//		gyro_thread.start();
//		pos_thread.start();
		regul.start();
//		com.start();
	}
}
