package tests;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.HiTechnicGyro;

public class TestGyro implements Runnable {

	@Override
	public void run() {
		
		@SuppressWarnings("resource")
		HiTechnicGyro gyro = new HiTechnicGyro(SensorPort.S2);
		while(true){
			try {
				Thread.sleep(1000);
				int curr = gyro.getCurrentMode();	
				System.out.println(curr);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public static void main(String[] args) {
		Thread thread = new Thread(new TestGyro());
		thread.start();
	}

}
