package client;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReferenceGUI {
	private int controlSignal, currentAngle, referenceValue;
	JLabel jl;
	public ReferenceGUI() {
		JFrame frame = new JFrame();
		jl = new JLabel();
		JTextField jt = new JTextField();
		init(frame, jl);	
		setSignals(0, 0, 0);
		
		
		
	}
	
	private void init(JFrame frame, JLabel jl) {
		frame.setTitle("Change Reference");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new FlowLayout());
		 frame.add(jl);
		 
		
		 frame.setVisible(true);
	}
	
	public void plot() {
		String prevText = "";
		if (jl.getText().length() > 0)
			prevText = jl.getText().split("<html>")[1].split("</html>")[0];
		
		
		jl.setText("<html>"+ prevText+ "<br>ControlSignal: " + controlSignal + ", Angle: " + currentAngle + ", Ref: "+ referenceValue + "</html>");
		//Ska plotta ut i graf men först bara text
	}
	
	public void setSignals(int cont, int ang, int ref) {
		this.controlSignal = cont;
		this.currentAngle = ang;
		this.referenceValue = ref;
	}

}