package hardware;

public class ParameterMonitor {
	private double Tu;
	private double Ku;
	
	public synchronized void setTu(double Tu) {
		this.Tu = Tu;
	}
	
	public synchronized double getTu() {
		return Tu;
	}
	
	public synchronized void setKu(double Ku) {
		this.Ku = Ku;
	}
	
	public synchronized double getKu() {
		return Ku;
	}
	

}
