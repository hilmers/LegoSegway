package hardware;

import lejos.hardware.Bluetooth;

public class Communication {
	private Bluetooth bt;
	
	public Communication() {
		bt = new Bluetooth();
		bt.getNXTCommConnector();
	}
}
