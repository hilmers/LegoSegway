package regul;

import lejos.hardware.sensor.HiTechnicGyro;
import lejos.robotics.RegulatedMotor;

public class Segway {
	private RegulatedMotor leftMotor;
    private RegulatedMotor rightMotor;
    private HiTechnicGyro gyro;
	
	public Segway(RegulatedMotor leftMotor, RegulatedMotor rightMotor, HiTechnicGyro gyro) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.gyro = gyro;
	}
	
	public void move(int rotation) {
		
	}
	
	

}
