package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.SimulationFrame;

public class FrameController {

	private SimulationFrame frame;

	public FrameController(SimulationFrame frame) {

		this.frame = frame;
		addActionListeners(this.frame);
	}

	public void addActionListeners(final SimulationFrame frame) {
		frame.getButon().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.displayData();
				SimulationManager manager = new SimulationManager(frame);
				Thread t = new Thread(manager);
				t.start();
			}
		});
	}
}
