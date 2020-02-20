package View;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Controller.FrameController;

public class SimulationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTextField minArrivalTime = new JTextField(15);
	private JTextField maxArrivalTime = new JTextField(15);
	private JTextField minProcessingTime = new JTextField(15);
	private JTextField maxProcessingTime = new JTextField(15);
	private JTextField nrQueues = new JTextField(15);
	private JTextField simulationInterval = new JTextField(15);
	private JTextField[] queuesTextField;
	private JTextArea area1 = new JTextArea(15, 50);
	private JButton buton = new JButton("start");

	private JScrollPane scrollPane = new JScrollPane(area1);

	public int getMinArrivalTime() {
		return Integer.parseInt(minArrivalTime.getText());
	}

	public int getMaxArrivalTime() {
		return Integer.parseInt(maxArrivalTime.getText());
	}

	public int getMinProcessingTime() {
		return Integer.parseInt(minProcessingTime.getText());
	}

	public int getMaxProcessingTime() {
		return Integer.parseInt(maxProcessingTime.getText());
	}

	public int getNrQueues() {
		return Integer.parseInt(nrQueues.getText());
	}

	public int getSimulationInterval() {
		return Integer.parseInt(simulationInterval.getText());
	}

	public JButton getButon() {
		return this.buton;
	}

	public SimulationFrame() {

		this.panel.add(new JLabel("min arrival time"));
		this.panel.add(minArrivalTime);
		this.panel.add(new JLabel("max arrival time"));
		this.panel.add(maxArrivalTime);
		this.panel.add(new JLabel("min proc time"));
		this.panel.add(minProcessingTime);
		this.panel.add(new JLabel("max proc time"));
		this.panel.add(maxProcessingTime);
		this.panel.add(new JLabel("nr queues"));
		this.panel.add(nrQueues);
		this.panel.add(new JLabel("simulation interval"));
		this.panel.add(simulationInterval);
		this.panel.add(buton);
		this.panel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.panel.setLayout(new FlowLayout());
		
		
		this.add(panel);

		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void displayData() {
		queuesTextField = new JTextField[Integer.parseInt(this.nrQueues.getText())];
		for (int i = 0; i < Integer.parseInt(this.nrQueues.getText()); i++) {
			queuesTextField[i] = new JTextField(50);
			
			panel.add(queuesTextField[i]);
		}
		this.repaint();
		this.revalidate();
	}

	public void setLogOfEvents(String logEvents) {
		this.area1.append(logEvents);
	}
	
	public JTextField getQueuesTextField(int i) {
		return this.queuesTextField[i];
	}


	public static void main(String[] args) {
		SimulationFrame frame = new SimulationFrame();
		new FrameController(frame);

	}
}
