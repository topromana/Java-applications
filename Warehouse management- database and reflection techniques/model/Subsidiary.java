package model;

public class Subsidiary {
	
	private int idSubsidiary;
	private String city;
	private String address;
	
	public Subsidiary(int idSubsidiary, String city, String address) {
		super();
		this.idSubsidiary = idSubsidiary;
		this.city = city;
		this.address = address;
	}

	public int getIdSubsidiary() {
		return idSubsidiary;
	}

	public void setIdSubsidiary(int idSubsidiary) {
		this.idSubsidiary = idSubsidiary;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	

}
