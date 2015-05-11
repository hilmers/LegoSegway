package hardware;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
public class Segway {
	private EncoderMotor leftMotor;
	private EncoderMotor rightMotor;
	private GyroSensor gyro;

	public Segway() {
		this.leftMotor = new UnregulatedMotor(MotorPort.C);
		this.rightMotor = new UnregulatedMotor(MotorPort.B);
		this.gyro = new GyroSensor(40 / 1000 );

	}

	public void forward(int leftSpeed, int rightSpeed) {
		leftMotor.setPower(leftSpeed);
		rightMotor.setPower(rightSpeed);
		leftMotor.forward();
		rightMotor.forward();
	}

	public void backward(int leftSpeed, int rightSpeed) {
		leftMotor.setPower(leftSpeed);
		rightMotor.setPower(rightSpeed);
		leftMotor.backward();
		rightMotor.backward();
	}

	public void stop() {
		leftMotor.stop();
		rightMotor.stop();
	}

	public GyroSensor getGyro() {
		return gyro;
	}


}
