package bll;

import java.util.ArrayList;

import models.MenuItem;
import models.Order;

public interface IRestaurant {

	/**
	 * 
	 * @param m
	 * @pre m!=null
	 * @pre.size = @post.size-1
	 */
	public void createMenuItem(MenuItem m);
	
	/**
	 * 
	 * @param m
	 * @pre name!=null
	 * @pre.size = @post.size+1
	 */
	
	public void deleteMenuItem(String name);
	
	/**
	 * 
	 * @param m
	 * @pre name!=null
	 * @pre price!=null
	 */
	
	public void updateMenuItem(String name, double price);
	
	/**
	 * 
	 * @param m
	 * @pre m!=null
	 * @pre o!=null
	 */
	
	public void createOrder(Order o,MenuItem m);
	
	/**
	 * 
	 * @param m
	 * @pre orderId!=null
	 */
	
	public Double computePriceForOrder(int orderId);
	
	/**
	 * 
	 * @param m
	 * @pre orderId!=null
	 */
	
	
	public void generateBill(int orderId);
}
