package hardware;

import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class Main {
	public static void main(String[] args) {
		Segway segway = new Segway();
		SegwayMonitor segmon = new SegwayMonitor();
		ParameterMonitor parmon = new ParameterMonitor();
		Thread regul = new Thread(new Regul(segway, segmon, parmon, 40)); // H
		regul.setPriority(1);// = // 0.02s
		Thread com = new Thread(new CommunicationThread(1337, segmon));
	
		regul.start();
		com.setPriority(Thread.MIN_PRIORITY);
		com.start();
	}
}
