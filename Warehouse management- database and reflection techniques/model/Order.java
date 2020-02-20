package model;

public class Order {
	private int idOrder;
	private int idProduct;
	private int idClient;
	private int quantity;
	
	public Order(int idOrder, int idProduct, int idClient, int quantity) {
		super();
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.idClient = idClient;
		this.quantity = quantity;
	}
	

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 

}
