package client;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		OperatorPlotGUI opGUI = new OperatorPlotGUI();
		Monitor mon = new Monitor();
		PlotThread pt = new PlotThread(mon, opGUI);
		for (int i = 0 ; i < 10; i++)
			opGUI.plot();
	}

}
