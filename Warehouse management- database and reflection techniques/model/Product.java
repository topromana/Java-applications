package model;

public class Product {
	private int idProduct;
	private String name;
	private int quantity;
	private int price;
	
	public Product(int idProduct, String productName, int quantity, int price) {
		super();
		this.idProduct= idProduct;
		this.name = productName;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean valid() {
		if (name.equals("")) {
			return false;
		}
		if (quantity == 0) {
			return false;
		}
		return true;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct= idProduct;
	}

	public String getProductName() {
		return name;
	}

	public void setProductName(String productName) {
		this.name = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
