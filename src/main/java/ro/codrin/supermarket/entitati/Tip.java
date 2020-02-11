package ro.codrin.supermarket.entitati;

public class Tip {

	private int id;
	private String denumire;
	private String denumireRaion;
	
	
	public Tip(int id, String denumire, String denumireRaion) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.denumireRaion = denumireRaion;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDenumire() {
		return denumire;
	}


	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}


	public String getDenumireRaion() {
		return denumireRaion;
	}


	public void setDenumireRaion(String denumireRaion) {
		this.denumireRaion = denumireRaion;
	}
	
	
	
	
	
}
