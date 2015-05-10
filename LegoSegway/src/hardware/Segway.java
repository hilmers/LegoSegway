package hardware;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
public class Segway {
	private EncoderMotor leftMotor;
	private EncoderMotor rightMotor;
	private double distancePerDegree, prevPos;
	private boolean checkedTimeFirstTime = false;
	private long lastSample, time;

	private GyroSensor gyro;

	public Segway() {
		this.leftMotor = new UnregulatedMotor(MotorPort.C);
		this.rightMotor = new UnregulatedMotor(MotorPort.B);
		this.gyro = new GyroSensor(40 / 1000 );
		distancePerDegree = 0.00071558;

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

	public double getPosition() {
		double pos = (leftMotor.getTachoCount() + rightMotor.getTachoCount()) / 2;
		return pos * distancePerDegree;
	}

	public GyroSensor getGyro() {
		return gyro;
	}

	public double getVelocity() throws Exception {
		if (!checkedTimeFirstTime) {
			time = System.currentTimeMillis();
			checkedTimeFirstTime = true;
		}
		long diff = System.currentTimeMillis() - time;
		time += diff;

		if (diff > 0) {
			double position = getPosition();
			double velocity = (position - prevPos) / diff;
			prevPos = position;
			return velocity;
		} else {
			throw new Exception("Negative time");
		}
	}

}
