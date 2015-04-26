package client;

import java.awt.FlowLayout;

import javax.swing.*;

public class OperatorPlotGUI{
	private double controlSignal, currentAngle, referenceValue;
	JTextArea jt;
	public OperatorPlotGUI() {
		JFrame frame = new JFrame();
		jt = new JTextArea();
		init(frame, jt);	
		setSignals(0, 0, 0);
		
		
		
	}
	
	private void init(JFrame frame, JTextArea jt) {
		frame.setTitle("Segway Plot");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jt.setEditable(false);
		frame.setLayout(new FlowLayout());
		
		frame.add(jt);
		 
		
		 frame.setVisible(true);
	}
	
	public void plot() {
//		String prevText = "";
//		if (jt.getText().length() > 0)
//			prevText = jt.getText().split("<html>")[1].split("</html>")[0];
		
		jt.append("\nControlSignal: " + controlSignal + ", Angle: " + currentAngle + ", Ref: "+ referenceValue);

		//jt.setText("<html>"+ prevText+ "<br>ControlSignal: " + controlSignal + ", Angle: " + currentAngle + ", Ref: "+ referenceValue + "</html>");
		//Ska plotta ut i graf men först bara text
	}
	
	public void setSignals(double cont, double ang, double ref) {
		this.controlSignal = cont;
		this.currentAngle = ang;
		this.referenceValue = ref;
	}
	
}
