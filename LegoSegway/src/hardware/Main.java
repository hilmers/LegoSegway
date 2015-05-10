package hardware;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {
	public static void main(String[] args) {
		SegwayMotor left = new SegwayMotor(new UnregulatedMotor(MotorPort.C));
		SegwayMotor right = new SegwayMotor(new UnregulatedMotor(MotorPort.B));
		long time = 40;
		GyroSensor gyro = new GyroSensor(time / 1000 );
		Segway segway = new Segway(left, right, gyro); // har tagit bort gyro
														// (3)
		SegwayMonitor segmon = new SegwayMonitor();
		ParameterMonitor parmon = new ParameterMonitor();
		// Thread gyro_thread = new Thread(new GyroThread(gyro, mon));
		// Thread pos_thread = new Thread(new PositionThread(segway, mon, 10));
		Thread regul = new Thread(new Regul(segway, segmon, parmon, time)); // H
		regul.setPriority(1);// = // 0.02s
		Thread com = new Thread(new CommunicationThread(1337, parmon));
		// gyro_thread.start();
		// pos_thread.start();
		regul.start();
		// com.start();
	}
}
