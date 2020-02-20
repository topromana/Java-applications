package model;

public class Client {
	private int idClient;
	private String name;
	private String address;

	public Client(int idClient, String name, String address) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.address = address;
	}

	public Client() {
		super();

	}

	public Client(String name, String address) {
		super();

		this.name = name;
		this.address = address;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean valid() {
		if (address.equals("")) {
			return false;
		}
		if (name.equals("")) {
			return false;
		}
		return true;
	}

}