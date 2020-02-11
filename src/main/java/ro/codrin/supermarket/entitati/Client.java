package ro.codrin.supermarket.entitati;

public class Client {

	private int id;
	private String nume;
	private String prenume;
	
	public Client(int id, String nume, String prenume) {
		super();
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	
	
	
}
