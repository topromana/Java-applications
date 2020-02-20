package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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


import bll.IRestaurant;
import data.RestaurantSerializator;

import models.BaseProduct;
import models.CompositeProduct;
import models.MenuItem;
import models.Order;
import models.Restaurant;


public class AdministratorGUI extends JFrame implements IRestaurant {
	
	private static final long serialVersionUID = 1L;
	private JPanel administratorPanel = new JPanel();
	private JTextField name = new JTextField(15);
	private JTextField price = new JTextField(15);
	String[] columnNames = {  "name", "price" };
	Object[][] data = { { " ", " " }, {  " ", " " } };
	private JTable productsTable = new JTable(data, columnNames);
	private DefaultTableModel model = new DefaultTableModel();
	private JButton addToRecipeButton = new JButton("add to recipe");
	private JButton addToMenuButton = new JButton("add to menu");
	private JButton updateButton = new JButton("update");
	private JButton deleteButton = new JButton("delete");
	private JScrollPane scrollPane = new JScrollPane();
	private JComboBox baseProducts = new JComboBox();
	private JComboBox choiceComboBox = new JComboBox();
	private JTextField recipe = new JTextField(30);
	private JTextField ingredient = new JTextField(15);
	Restaurant r = null;
	public AdministratorGUI(Restaurant r) {
		this.r = r;
		this.administratorPanel.add(new JLabel("name "));
		this.administratorPanel.add(name);
		this.administratorPanel.add(new JLabel(" price"));
		this.administratorPanel.add(price);
		this.administratorPanel.add(addToRecipeButton);
		this.administratorPanel.add(addToMenuButton);
		this.administratorPanel.add(updateButton);
		this.administratorPanel.add(deleteButton);
		this.administratorPanel.add(scrollPane);
		this.scrollPane.setViewportView(productsTable);
		this.administratorPanel.setLayout(new FlowLayout());
		this.administratorPanel.add(productsTable);
		this.administratorPanel.add(choiceComboBox);
		this.administratorPanel.add(new JLabel(" ingredient"));
		this.administratorPanel.add(ingredient);
		this.administratorPanel.add(recipe);
		
		this.administratorPanel.setLayout(new FlowLayout());

		this.add(administratorPanel);

		this.setSize(600, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		ActionListener listener = new ProductsAction(this);
		addToMenuButton.addActionListener(listener);
		deleteButton.addActionListener(listener);
		updateButton.addActionListener(listener);
		addToRecipeButton.addActionListener(listener);
		populateTable();
		populateBaseProductsComboBox();
		choiceComboBox.addItem("simple");
		choiceComboBox.addItem("composite");
	}
	
	public void populateBaseProductsComboBox() {
		BaseProduct p =new BaseProduct();
		List<MenuItem> productList = r.getMenu();
		for (MenuItem c : productList) {
			baseProducts.addItem(c.getName());
		}

	}
	
	public JButton getAddToRecipeButton() {
		return addToRecipeButton;
	}
	public String getName() {
		
	return name.getText();
	}
	
	public Double getPrice() {
		
		return Double.parseDouble(price.getText());
		}
	public JButton getAddToMenuButton() {
		return addToMenuButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}


	public JButton getDeleteButton() {
		return deleteButton;
	}

	

	public void populateTable() {
	
		ArrayList<MenuItem> productList = r.getMenu();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		for (MenuItem  p : productList) {
			model.addRow(new Object[] { p.getName(), p.computePrice() });
		}
		this.productsTable.setModel(model);
	}
	

	private class ProductsAction implements ActionListener {

		private AdministratorGUI frame;

		public ProductsAction(AdministratorGUI frame) {
			this.frame = frame;

		}
		String recipeList = new String();

		ArrayList<MenuItem> reteta = new ArrayList<MenuItem>();
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
		
			if(source.equals(frame.getAddToRecipeButton())) {
				recipeList+=ingredient.getText()+",";
				recipe.setText(recipeList);
				for(MenuItem m: r.getMenu())
					if(m.getName().equals(ingredient.getText()))
						reteta.add(m);
			}
			if (source.equals(frame.getAddToMenuButton())) {
				if(choiceComboBox.getSelectedItem().equals("simple")) {
				System.out.println("butoooon");
				MenuItem p = this.createProductFromInterface();
				try {
					createMenuItem(p);
					
					frame.populateTable();
					RestaurantSerializator.serialization(r);
				} catch (Exception ex) {
					
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
				else {
					MenuItem p = this.createCompositeProductFromInterface();
					try {
						createMenuItem(p);
						
						frame.populateTable();
						RestaurantSerializator.serialization(r);
					} catch (Exception ex) {
						
						JOptionPane.showMessageDialog(frame, ex.getMessage());
					}
					
				}
			}

			if (source.equals(frame.getDeleteButton())) {
				String name = new String(getName());
				try {
					System.out.println("buton fata");
					r.deleteMenuItem(name);
					System.out.println("spate");
					frame.populateTable();
					RestaurantSerializator.serialization(r);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}
			}
			if (source.equals(frame.getUpdateButton())) {
				String name = new String(getName());
				Double price = new Double(getPrice());
				try {
					updateMenuItem(name,price);
					for(MenuItem m: r.getMenu())
						System.out.println(m.getName()+"  "+m.computePrice());
					frame.populateTable();
					RestaurantSerializator.serialization(r);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, ex.getMessage());
				}

			}
		}

		
	private MenuItem createProductFromInterface() {
		
		String name = new String(getName());
		Double price = new Double(getPrice());
		
		MenuItem c = new BaseProduct( name, price);
		return c;
	}
	
private MenuItem createCompositeProductFromInterface() {
	MenuItem c = new CompositeProduct( reteta );
		c.setName(getName());
		c.computePrice();
	
		
		return c;
	}
	
}


	@Override
	public void createMenuItem(MenuItem m) {
		// TODO Auto-generated method stub
		r.createMenuItem(m);
	}

	@Override
	public void deleteMenuItem(String name) {
		// TODO Auto-generated method stub
		r.deleteMenuItem(name);
		
	}

	@Override
	public void updateMenuItem(String name, double price) {
		// TODO Auto-generated method stub
		r.updateMenuItem(name, price);
		
	}

	@Override
	public void createOrder(Order o, MenuItem m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double computePriceForOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateBill(int orderId) {
		// TODO Auto-generated method stub
		
	}}

