package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.IRestaurant;
import data.RestaurantSerializator;
import models.BaseProduct;
import models.MenuItem;
import models.Order;
import models.Restaurant;


public class WaiterGUI extends JFrame implements IRestaurant{

	private static final long serialVersionUID = 1L;
	private JPanel waiterPanel = new JPanel();
	private JTextField orderId = new JTextField(15);
	private JTextField orderIdComputeBill = new JTextField(15);
	private JTextField date = new JTextField(15);
	private JTextField tableNr = new JTextField(15);
	String[] columnNames = {  "orderId", "date","tableNr","products" };
	Object[][] data = { { " ", " ", " "," " }, {  " ", " ", " "," " } };
	private JTable ordersTable = new JTable(data, columnNames);
	private DefaultTableModel model = new DefaultTableModel();
	private JButton addOrderButton = new JButton("add order");
	private JButton computeBillButton = new JButton("compoute bill");
	private JScrollPane scrollPane = new JScrollPane();
	private JTextField product = new JTextField(15);
	private JTextField total = new JTextField(15);
	private JButton generateBill = new JButton("Generate Bill");
	Restaurant r = null;
	public WaiterGUI(Restaurant r) {
		this.r = r;
		this.waiterPanel.add(new JLabel("orderId "));
		this.waiterPanel.add(orderId);
		this.waiterPanel.add(new JLabel(" date"));
		this.waiterPanel.add(date);
		this.waiterPanel.add(new JLabel(" tableNr"));
		this.waiterPanel.add(tableNr);
		this.waiterPanel.add(addOrderButton);
		this.waiterPanel.add(computeBillButton);
		this.waiterPanel.add(scrollPane);
		this.scrollPane.setViewportView(ordersTable);
		this.waiterPanel.setLayout(new FlowLayout());
		this.waiterPanel.add(new JLabel(" ordered product"));
		this.waiterPanel.add(product);
		this.waiterPanel.add(ordersTable);
		this.waiterPanel.add(new JLabel(" total for order"));
		this.waiterPanel.add(total);
		this.waiterPanel.add(new JLabel("orderId for bill"));
		this.waiterPanel.add(orderIdComputeBill);
		this.waiterPanel.add(generateBill);
		this.waiterPanel.setLayout(new FlowLayout());

		this.add(waiterPanel);

		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		ActionListener listener = new WaiterAction(this);
		addOrderButton.addActionListener(listener);
		computeBillButton.addActionListener(listener);
		generateBill.addActionListener(listener);
		populateTable();
	}
	
	public String getDate() {
		return date.getText();
	}
	public String getName() {
		return product.getText();
	}
	public int getOrderId() {
		return Integer.parseInt(orderId.getText());
	}
	public int getTableNr() {
		return Integer.parseInt(tableNr.getText());
	}
	
	public JButton getAddOrderButton() {
		return addOrderButton;
	}
	public JButton getGenerateBillButton() {
		return generateBill;
	}
	public void setAddOrderButton(JButton addOrderButton) {
		this.addOrderButton = addOrderButton;
	}
	public JButton getComputeBillButton() {
		return computeBillButton;
	}
	public void setComputeBillButton(JButton computeBillButton) {
		this.computeBillButton = computeBillButton;
	}


	private class WaiterAction implements ActionListener {

		private WaiterGUI frame;

		public WaiterAction(WaiterGUI frame) {
			this.frame = frame;

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
		
		
			if (source.equals(frame.getAddOrderButton())) {
				
				System.out.println("butoooon");
				MenuItem p = this.createItemFromInterface();
				Order o = this.createOrderFromInterface();
				try {
					createOrder(o,p);
					frame.populateTable();
				
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
				
			

			if (source.equals(frame.getComputeBillButton())) {
				//MenuItem p = this.createProductFromInterface();
				
				Integer orderId = new Integer(Integer.parseInt(orderIdComputeBill.getText()));
				try {
				Double price = computePriceForOrder(orderId);
				System.out.println("pretul "+Double.toString(price));
				total.setText(Double.toString(price));
				}catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
			if (source.equals(frame.getGenerateBillButton())) {
				//MenuItem p = this.createProductFromInterface();
				Integer orderId = new Integer(Integer.parseInt(orderIdComputeBill.getText()));
				
				try {
				
					generateBill(orderId);
				}catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
			

			}
		
		public Order createOrderFromInterface() {
			Order o = new Order();
			o.setDate(getDate());
			o.setOrderID(getOrderId());
			o.setTableNr(getTableNr());
			return o;
		}
		
		public MenuItem createItemFromInterface() {
			MenuItem m = new BaseProduct();
			String name = new String(getName());
			m.setName(name);
			for(MenuItem men:r.getMenu())
				if(men.getName().equals(name))
					m.setPrice(men.computePrice());
			return m;
		}
		}
	
	public void populateTable() {
		HashMap<Order,Set<MenuItem>> order = r.getOrder();
		//ArrayList<MenuItem> productList = r.getMenu();
	
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for (Order o : order.keySet()) {
						
			String menuItems = "";
			
			for(MenuItem m: order.get(o)) {
				menuItems += m.getName() + " ";
			}
			model.addRow(new Object[] { o.getOrderID(), o.getDate(), o.getTableNr(),menuItems });

			
			System.out.println(menuItems);
		}
		this.ordersTable.setModel(model);
	}
	@Override
	public void createMenuItem(MenuItem m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenuItem(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMenuItem(String name, double price) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Double computePriceForOrder(int orderId) {
		// TODO Auto-generated method stub
	return	r.computePriceForOrder(orderId);
	
	}

	@Override
	public void generateBill(int orderId) {
		// TODO Auto-generated method stub
		r.generateBill(orderId);
	}

	@Override
	public void createOrder(Order o, MenuItem m) {
		r.createOrder(o, m);
		
	}
	
}
