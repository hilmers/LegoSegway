package client;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		OperatorPlotGUI opGUI = new OperatorPlotGUI();
		  Opcom opcom = new Opcom();
		  Monitor mon = new Monitor();
		  opcom.initializeGUI();
		  Reader reader = new Reader(opcom, mon);
		  opcom.start();
		  reader.start();
		  CommunicationThread t = new CommunicationThread(mon);
		
		//ReferenceGUI refGUI = new ReferenceGUI(mon);
		PlotThread pt = new PlotThread(mon, opGUI);
		Thread t1 = new Thread(pt);
		Thread t2 = new Thread(t);
		t1.start();
		t2.start();
		
		


	}
	

}
