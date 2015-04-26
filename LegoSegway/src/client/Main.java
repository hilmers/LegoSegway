package client;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		OperatorPlotGUI opGUI = new OperatorPlotGUI();
		final OpCom opcom = new OpCom();
		long starttime = System.currentTimeMillis();
//		opcom.initializeGUI();
//		opcom.start();
		Runnable initializeGUI = new Runnable(){
			public void run(){
			    opcom.initializeGUI();
			    opcom.start();
			}
		};
		try{
		    SwingUtilities.invokeAndWait(initializeGUI);
		}catch(Exception e){
		    return;
		}
		
		Monitor mon = new Monitor();
		ReferenceGUI refGUI = new ReferenceGUI(mon);
		PlotThread pt = new PlotThread(mon, opGUI, opcom);
		pt.run();
		
		


	}
	

}
