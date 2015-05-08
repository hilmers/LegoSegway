package hardware;

public class CompFilter {
	private double ygs, yas, yf, h, eh;

	// h is the sample-time
	public CompFilter(double h) {
		this.h = h;
		eh = Math.exp(-h);
	}

	public double compFilt(double ya, double yg) {
		yf = eh * yf + (1 - eh) * yas + yg - ygs;
		ygs = yg; // Update s t a t e
		yas = ya; // Update s t a t e
		return yf;
	}

}
