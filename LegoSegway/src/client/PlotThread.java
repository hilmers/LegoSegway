package client;

public class PlotThread implements Runnable {
	private Monitor mon;
	private OperatorPlotGUI plotGUI;

	public PlotThread(Monitor mon, OperatorPlotGUI plotGUI) {
		this.mon = mon;

		this.plotGUI = plotGUI;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			plotGUI.setSignals(mon.getControlSignal(), mon.getCurrentAngle(),
					mon.getReferenceValue());
			plotGUI.plot();

			System.out.println("k�rs");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
