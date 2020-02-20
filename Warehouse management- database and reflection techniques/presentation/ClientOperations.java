package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ClientBLL;
import model.Client;

public class ClientOperations extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel clientPanel = new JPanel();
	private JTextField clientId = new JTextField(15);
	private JTextField clientName = new JTextField(15);
	private JTextField clientAddress = new JTextField(15);
	private String[] columnNames = { "id client", "name", "address" };
	private Object[][] data = { { " ", " ", " " }, { " ", " ", " " } };
	private JTable clientsTable = new JTable(data, columnNames);
	private DefaultTableModel model = new DefaultTableModel();
	private JButton addButton = new JButton("add client");
	private JButton deleteButton = new JButton("delete");
	private JButton editButton = new JButton("edit");
	private JScrollPane scrollPane = new JScrollPane();

	public int getClientId() {
		return Integer.parseInt(clientId.getText());
	}
	
	public JButton getAddButton() {
		return this.addButton;
	}
	public JButton getDeleteButton() {
		return this.deleteButton;
	}
	public JButton getEditButton() {
		return this.editButton;
	}

	public String getClientName() {
		return clientName.getText();
	}

	

	public String getClientAddress() {
		return clientAddress.getText();
	}

	

	public ClientOperations() {
		super("clients");
		this.clientPanel.add(new JLabel("id client"));
		this.clientPanel.add(clientId);
		this.clientPanel.add(new JLabel(" client name"));
		this.clientPanel.add(clientName);
		this.clientPanel.add(new JLabel(" client address"));
		this.clientPanel.add(clientAddress);
		this.clientPanel.add(addButton);
		this.clientPanel.add(deleteButton);
		this.clientPanel.add(editButton);
		this.clientPanel.add(scrollPane);
		this.scrollPane.setViewportView(clientsTable);
		this.clientPanel.setLayout(new FlowLayout());
		this.add(clientPanel);

		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		ActionListener listener = new ClientsAction(this);
		addButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		editButton.addActionListener(listener);
		populateTable();
	}
	
	public void populateTable() {
		ClientBLL bl = new ClientBLL();
		List<Client> clientList = bl.getAllClients();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for(Client c: clientList) {
			model.addRow(new Object[]{c.getIdClient(), c.getName(), c.getAddress()});
		}
		this.clientsTable.setModel(model);
	}
	
	private class ClientsAction implements ActionListener {

		private ClientOperations frame;
		
		public ClientsAction(ClientOperations frame) {
			this.frame = frame;
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			ClientBLL bl = new ClientBLL();
			
			if (source.equals(frame.getAddButton())) {
				Client c = this.createClientFromInterface(false);
				try {
					bl.insertClient(c);
					frame.populateTable();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
			
			if (source.equals(frame.getDeleteButton())) {
				Client c = this.createClientFromInterface(true);
				bl.deleteClient(c,c.getName());
				frame.populateTable();
			}
			if (source.equals(frame.getEditButton())) {
				Client c = this.createClientFromInterface(true);
				bl.updateClient(c,c.getName());
				frame.populateTable();

			}
		}
		
			private Client createClientFromInterface(Boolean withId) {
				Integer id = 0;
				String name = new String(getClientName());
				String address = new String(getClientAddress());
				if (withId) {
					id = getClientId();
				}
				Client c = new Client(id, name, address);
				return c;
			}
		
	}
}
