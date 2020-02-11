package ro.codrin.supermarket.entitati;

public class Supermarket {
	private int id;
	private String locatie;
	
	public Supermarket(int id, String locatie) {
		super();
		this.id = id;
		this.locatie = locatie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocatie() {
		return locatie;
	}

	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}
	
}
