package models;

import java.util.List;

public class CompositeProduct extends MenuItem {
	
	public CompositeProduct(String name, Double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	
	
	List<MenuItem> otherProducts;
	public CompositeProduct(  List<MenuItem> otherProducts) {
		super();
		this.otherProducts = otherProducts;
	}


	
	public void addProductToListOfMenuItems(MenuItem item) {
		otherProducts.add(item);
	}
	
	public Double computePrice() {
		Double sum = 0.0;
		for (MenuItem item : otherProducts) {
			sum += item.computePrice();
		}
		return sum;
	}

}
