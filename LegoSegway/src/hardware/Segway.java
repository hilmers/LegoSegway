package hardware;


public class Segway {
	private SegwayMotor leftMotor;
    private SegwayMotor rightMotor;
    private GyroSensor gyro;			//The gyro might not be used in this class
	private double distancePerDegree;
	
	public Segway(SegwayMotor leftMotor, SegwayMotor rightMotor, GyroSensor gyro) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.gyro = gyro;
        distancePerDegree = 0.00071558;
	}
	
	public void forward(int leftSpeed, int rightSpeed) {
		leftMotor.rotateForward(leftSpeed);
		rightMotor.rotateForward(rightSpeed);
	}
	
	public void backward(int leftSpeed, int rightSpeed) {
		leftMotor.rotateBackward(leftSpeed);
		rightMotor.rotateBackward(rightSpeed);
	}
	
	public double getPosition() {
		double pos = (leftMotor.getTachoCount() + rightMotor.getTachoCount())/2;
		return pos * distancePerDegree;
	}

}
