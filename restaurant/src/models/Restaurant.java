package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import bll.IRestaurant;
import data.WriteFile;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;


public class Restaurant implements Observable, IRestaurant, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 390049508174660687L;
	private ArrayList<MenuItem> menu ; // trebuie instantian
	private HashMap<Order, Set<MenuItem>> orders;
	

	public Restaurant() {
		menu  = new ArrayList<MenuItem>();
	
		//wf.getMenuFromFile();
		orders = new HashMap<Order, Set<MenuItem>>();
		
	}
	
	public ArrayList<MenuItem> getMenu(){
		return menu;
	}
	public HashMap<Order,Set<MenuItem>> getOrder(){
		//@Override
		return orders;
	}
	public void addListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMenuItem(MenuItem m) {
		// TODO Auto-generated method stub
		assert m!=null;
		int sizeBeginning = menu.size();
		
		menu.add(m);
		int sizeEnd = menu.size();
		assert sizeBeginning == sizeEnd-1; 
	System.out.println(m.getName());
		}
	

	@Override
	public void deleteMenuItem(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		
		for(MenuItem m : menu) {
			System.out.println(m.getName());
			if(m.getName().equals(name)) { menu.remove(m);
			break;}}
		System.out.println("caca");

		for(MenuItem it:menu)
			System.out.println(it.getName());
		System.out.println("in restaurant");
	}

	@Override
	public void updateMenuItem(String name, double price) {
		// TODO Auto-generated method stub
		assert name!=null;
		assert price != 0;
		for(MenuItem m: menu)
			if(m.getName().equals(name))
				m.setPrice(price);
		
	}

	@Override
	public void createOrder(Order o,MenuItem m) {
		// TODO Auto-generated method stub
		assert o != null;
		assert m!= null;
		Order selected = new Order();
		boolean contained = false;
		for(Order o2: orders.keySet()) {
			System.out.println(o2.getOrderID() + " " + o2.hashCode());
			if(o2.getOrderID() == o.getOrderID()) {
				contained = true;
				selected = o2;
				System.out.println("equal " + o.getOrderID() + " " + o.hashCode());
			}
		}
		if(contained == true) {
			
			
			orders.get(selected).add(m);
			for(MenuItem men: orders.get(selected)) {
				System.out.println( men.getName() + " ");
			}
		
		}
		else {
			Set<MenuItem> newProductsList = new HashSet<MenuItem>();
			newProductsList.add(m);
			orders.put(o,newProductsList);
			
		}
	}

	@Override
	public Double computePriceForOrder(int orderId) {
		// TODO Auto-generated method stub
		assert orderId != 0;
		Double price = 0.0;
		for (Order o : orders.keySet()) {
			
			if(o.getOrderID()==orderId)
				for(MenuItem m: orders.get(o)) {
					price += m.computePrice();
				}
		}
		return price;
			
			
	
	}

	@Override
	public void generateBill(int orderId) {
		// TODO Auto-generated method stub
		assert orderId != 0;
		String file_name = "C:\\Users\\Romana\\Desktop\\bill.txt";
		try {
			WriteFile data = new WriteFile(file_name, true);
			for (Order o : orders.keySet()) {
				
				String menuItems = "";
				if(o.getOrderID()==orderId) {
				for(MenuItem m: orders.get(o)) {
					menuItems += m.getName() + " ";
				}
				data.writeToFile("Receipt");
				data.writeToFile("Order id:" + Integer.toString( o.getOrderID()));
				data.writeToFile("Date:" + o.getDate());
				data.writeToFile( "Table nr" + Integer.toString( o.getTableNr()));
				data.writeToFile(menuItems);
				data.writeToFile("total:" + Double.toString(computePriceForOrder(o.getOrderID())));}
			
			}
		
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
