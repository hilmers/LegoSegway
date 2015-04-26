package client;

public class PlotThread implements Runnable {
	private Monitor mon;
	private OperatorPlotGUI plotGUI;
	private OpCom op;
	
	public PlotThread(Monitor mon, OperatorPlotGUI plotGUI, OpCom op) {
		this.mon = mon;
		this.op = op;
		this.plotGUI = plotGUI;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			plotGUI.setSignals(mon.getControlSignal(), mon.getCurrentAngle(), mon.getReferenceValue());
			plotGUI.plot();
			PlotData pd = new PlotData(20, mon.getControlSignal(), mon.getCurrentAngle());
			op.putMeasurementDataPoint(pd);
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
