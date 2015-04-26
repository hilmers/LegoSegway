package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import se.lth.control.*;
import se.lth.control.plot.*;

/** Class that creates and maintains a GUI for the Ball and Beam process.
Uses two internal threads to update plotters */
public class OpCom {
	public static final int OFF = 0, BEAM = 1, BALL = 2;


	private PlotterPanel measurementPlotter; // has internal thread
	private PlotterPanel controlPlotter; // has internal thread

	// Declarartion of main frame.
	private JFrame frame;

	// Declarartion of panels.
	private BoxPanel guiPanel, plotterPanel;
	

	private double range = 10.0; // Range of time axis
	private int divTicks = 5;    // Number of ticks on time axis
	private int divGrid = 5;     // Number of grids on time axis


	/** Constructor. Creates the plotter panels. */
	public OpCom() {
		measurementPlotter = new PlotterPanel(2, 4); // Two channels
		controlPlotter = new PlotterPanel(1, 4);
	}

	/** Starts the threads. */
	public void start() {
		measurementPlotter.start();
		controlPlotter.start();
	}

	/** Stops the threads. */
	public void stopThread() {
		measurementPlotter.stopThread();
		controlPlotter.stopThread();
	}

	/** Sets up a reference to Regul. Called by Main. */


	/** Creates the GUI. Called from Main. */
	public void initializeGUI() {
		// Create main frame.
		frame = new JFrame("Ball and Beam GUI");

		// Create a panel for the two plotters.
		plotterPanel = new BoxPanel(BoxPanel.VERTICAL);
		// Create plot components and axes, add to plotterPanel.
		measurementPlotter.setYAxis(180, -90
				, 2, 2);
		measurementPlotter.setXAxis(range, divTicks, divGrid);
		measurementPlotter.setTitle("Set-point and measured variable");
		plotterPanel.add(measurementPlotter);
		plotterPanel.addFixed(10);
		controlPlotter.setYAxis(2, -1, 2, 2);
		controlPlotter.setXAxis(range, divTicks, divGrid);
		controlPlotter.setTitle("Control");
		plotterPanel.add(controlPlotter);
		


	// Create panel for the entire GUI.
		guiPanel = new BoxPanel(BoxPanel.HORIZONTAL);
	//	guiPanel.add(leftPanel);
		guiPanel.addGlue();
		guiPanel.add(plotterPanel);

		// WindowListener that exits the system if the main window is closed.
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//regul.shutDown();
				stopThread();
				System.exit(0);
			}
		});

		// Set guiPanel to be content pane of the frame.
		frame.getContentPane().add(guiPanel, BorderLayout.CENTER);

		// Pack the components of the window.
		frame.pack();

		// Position the main window at the screen center.
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fd = frame.getSize();
		frame.setLocation((sd.width-fd.width)/2, (sd.height-fd.height)/2);

		// Make the window visible.
		frame.setVisible(true);
	}

	/** Called by Regul to put a control signal data point in the buffer. */
	public synchronized void putControlDataPoint(DoublePoint dp) {
		double x = dp.x;
		double y = dp.y;
		controlPlotter.putData(x, y);
	}

	/** Called by Regul to put a measurement data point in the buffer. */
	public synchronized void putMeasurementDataPoint(PlotData pd) {
		double x = pd.x;
		double ref = pd.ref;
		double y = pd.y;
		measurementPlotter.putData(x, ref, y);
	
	}
	
}
