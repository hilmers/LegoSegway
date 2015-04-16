package client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReferenceGUI {
	private int controlSignal, currentAngle, referenceValue;
	private Monitor mon;
	private JLabel jl;
	public ReferenceGUI(Monitor mon) {
		this.mon = mon;
		JFrame frame = new JFrame();
		jl = new JLabel();
		final JTextField jt = new JTextField("New reference value");
		JButton upd = new JButton("Update ref");
		 upd.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                System.out.println("You clicked the button");
	                String input = jt.getText();
	                int parsed = Integer.parseInt(input);
	                setRef(parsed);
	            }
	        }); 
		init(frame, jl, jt, upd);
		
		
		
	}
	
	private void init(JFrame frame, JLabel jl, JTextField jt, JButton upd) {
		frame.setTitle("Change Reference");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new FlowLayout());
		 frame.add(jl);
		 frame.add(jt);
		 frame.add(upd);
		 
		
		 frame.setVisible(true);
	}
	
	
	private void setRef(int ref) {
	
		mon.setReferenceValue(ref);
	}

}
