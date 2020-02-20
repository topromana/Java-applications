package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;

public class PlaceOrderWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel orderPanel = new JPanel();
	private JComboBox clientComboBox = new JComboBox();
	private JComboBox productComboBox = new JComboBox();
	private JTextField quantity = new JTextField(15);
	private String[] columnNames = { "id order", "idProduct", "idClient", "quantity" };
	private Object[][] data = { { " ", " ", " ", " " }, { " ", " ", " ", " " } };
	private DefaultTableModel model = new DefaultTableModel();
	private JTable ordersTable = new JTable(data, columnNames);
	private JButton addButton = new JButton("add order");
	private JScrollPane scrollPane = new JScrollPane();

	public int getQuantity() {
		return Integer.parseInt(quantity.getText());
	}

	public JButton getAddButton() {
		return this.addButton;
	}

	public String getClient() {
		return clientComboBox.getSelectedItem().toString();
	}

	public String getProduct() {
		return productComboBox.getSelectedItem().toString();
	}

	public void populateClientComboBox() {
		ClientBLL bl = new ClientBLL();
		List<Client> clientList = bl.getAllClients();
		for (Client c : clientList) {
			clientComboBox.addItem(c.getName());
		}

	}

	public void populateProductComboBox() {
		ProductBLL bl = new ProductBLL();
		List<Product> productList = bl.getAllProducts();
		for (Product p : productList) {
			productComboBox.addItem(p.getProductName());
		}

	}

	public PlaceOrderWindow() {
		this.orderPanel.add(new JLabel("client"));
		this.orderPanel.add(clientComboBox);
		this.orderPanel.add(new JLabel("product"));
		this.orderPanel.add(productComboBox);
		this.orderPanel.add(new JLabel("quantity"));
		this.orderPanel.add(quantity);
		this.orderPanel.add(scrollPane);
		this.scrollPane.setViewportView(ordersTable);
		this.orderPanel.add(addButton);

		this.orderPanel.setLayout(new FlowLayout());
		this.add(orderPanel);

		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		ActionListener listener = new OrderAction(this);
		addButton.addActionListener(listener);
		populateClientComboBox();
		populateProductComboBox();
		populateTable();
	}

	public void populateTable() {
		OrderBLL bl = new OrderBLL();
		List<Order> orderList = bl.getAllOrders();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for (Order p : orderList) {
			model.addRow(new Object[] { p.getIdOrder(), p.getIdProduct(), p.getIdClient(), p.getQuantity() });
		}
		this.ordersTable.setModel(model);
	}

	private class OrderAction implements ActionListener {

		private PlaceOrderWindow frame;

		public OrderAction(PlaceOrderWindow frame) {
			this.frame = frame;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			OrderBLL bl = new OrderBLL();

			if (source.equals(frame.getAddButton())) {
				Order o = this.createOrderFromInterface();
				try {
					bl.insertOrder(o);
					frame.populateTable();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
		}

		private Order createOrderFromInterface() {
			Integer id = 0;
			String nameProduct = new String(getProduct());
			String nameClient = new String(getClient());
			ProductBLL blProduct = new ProductBLL();
			Integer productId = blProduct.findByName(nameProduct).getIdProduct();
			Integer productQuantity = blProduct.findByName(nameProduct).getQuantity();
			ClientBLL blClient = new ClientBLL();
			Integer clientId = blClient.findByName(nameClient).getIdClient();

			Integer quantity = getQuantity();
			if (productQuantity - quantity < 0) {
				JOptionPane.showMessageDialog(null, "not enough in stock");
			}

			else {
				Order o = new Order(id, productId, clientId, quantity);
				blProduct.updateProductQuantity(blProduct.findByName(nameProduct), nameProduct,
						productQuantity - quantity);
				return o;
			}
			return null;

		}
	}

}
