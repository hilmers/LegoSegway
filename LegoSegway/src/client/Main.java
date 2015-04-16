package client;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		OperatorPlotGUI opGUI = new OperatorPlotGUI();
		
		
		Monitor mon = new Monitor();
		ReferenceGUI refGUI = new ReferenceGUI(mon);
		PlotThread pt = new PlotThread(mon, opGUI);
		pt.run();
		while(true)
			opGUI.plot();
	}

}
