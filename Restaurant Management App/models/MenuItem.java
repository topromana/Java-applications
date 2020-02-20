package models;

public abstract class MenuItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2052673644438529407L;
	private String name;
	private Double price;
	
	
	public MenuItem(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String menuToString(){
		String msg = new String(this.name );
		return msg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double computePrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
