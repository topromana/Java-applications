package models;

public class Order {
	
	private int orderID;
	private String date;
	private int tableNr;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + orderID;
		result = prime * result + tableNr;
		return result;
	}


	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Order(int orderID, String date, int tableNr) {
		super();
		this.orderID = orderID;
		this.date = date;
		this.tableNr = tableNr;
	}


	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTableNr() {
		return tableNr;
	}
	public void setTableNr(int tableNr) {
		this.tableNr = tableNr;
	}
	
	
}
