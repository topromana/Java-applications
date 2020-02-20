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
import bll.ProductBLL;
import model.Client;
import model.Product;
//import presentation.ClientOperations.ClientsAction;

public class ProductOperationsWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel productPanel = new JPanel();
	private JTextField productId = new JTextField(15);
	private JTextField productName = new JTextField(15);
	private JTextField productQuantity = new JTextField(15);
	private JTextField productPrice = new JTextField(15);
	String[] columnNames = { "id product", "name", "quantity", "price" };
	Object[][] data = { { " ", " ", " ", " " }, { " ", " ", " ", " " } };
	private JTable productsTable = new JTable(data, columnNames);
	private DefaultTableModel model = new DefaultTableModel();
	private JButton addButton = new JButton("add product");
	private JButton deleteButton = new JButton("delete");
	private JButton editButton = new JButton("edit");
	private JScrollPane scrollPane = new JScrollPane();

	public int getProductId() {
		return Integer.parseInt(productId.getText());
	}

	public int getProductQuantity() {
		return Integer.parseInt(productQuantity.getText());
	}

	public int getProductPrice() {
		return Integer.parseInt(productPrice.getText());
	}

	public String getProductName() {
		return productName.getText();
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

	public ProductOperationsWindow(String Name) {
		super("products");
		this.productPanel.add(new JLabel("id "));
		this.productPanel.add(productId);
		this.productPanel.add(new JLabel(" product name"));
		this.productPanel.add(productName);
		this.productPanel.add(new JLabel(" quantity"));
		this.productPanel.add(productQuantity);
		this.productPanel.add(new JLabel(" price"));
		this.productPanel.add(productPrice);
		this.productPanel.add(addButton);
		this.productPanel.add(deleteButton);
		this.productPanel.add(editButton);
		this.productPanel.add(scrollPane);
		this.scrollPane.setViewportView(productsTable);
		this.productPanel.setLayout(new FlowLayout());
		this.productPanel.add(productsTable);

		this.productPanel.setLayout(new FlowLayout());

		this.add(productPanel);

		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		ActionListener listener = new ProductsAction(this);
		addButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		editButton.addActionListener(listener);
		populateTable();
	}

	public void populateTable() {
		ProductBLL bl = new ProductBLL();
		List<Product> productList = bl.getAllProducts();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for (Product p : productList) {
			model.addRow(new Object[] { p.getIdProduct(), p.getProductName(), p.getPrice(), p.getQuantity() });
		}
		this.productsTable.setModel(model);
	}

	private class ProductsAction implements ActionListener {

		private ProductOperationsWindow frame;

		public ProductsAction(ProductOperationsWindow frame) {
			this.frame = frame;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			ProductBLL bl = new ProductBLL();

			if (source.equals(frame.getAddButton())) {
				System.out.println("butoooon");
				Product p = this.createProductFromInterface(false);
				try {
					bl.insertProduct(p);
					frame.populateTable();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}

			if (source.equals(frame.getDeleteButton())) {
				Product p = this.createProductFromInterface(true);
				bl.deleteProduct(p, p.getProductName());
				frame.populateTable();
			}
			if (source.equals(frame.getEditButton())) {
				Product p = this.createProductFromInterface(true);
				bl.updateProduct(p, p.getProductName());
				frame.populateTable();

			}
		}

		private Product createProductFromInterface(Boolean withId) {
			Integer id = 0;
			String name = new String(getProductName());
			Integer quantity = new Integer(getProductQuantity());
			Integer price = new Integer(getProductPrice());
			if (withId) {
				id = getProductId();
			}
			Product c = new Product(id, name, quantity, price);
			return c;
		}

	}
}