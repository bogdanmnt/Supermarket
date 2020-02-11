package ro.codrin.supermarket.entitati;

public class Produs {
	private int id;
	String denumire;
	double pret;
	int stoc;
	String denumireTip;
	String denumireProducator;
	
	public Produs(int id, String denumire, double pret, int stoc, String denumireTip, String denumireProducator) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.pret = pret;
		this.stoc = stoc;
		this.denumireTip = denumireTip;
		this.denumireProducator = denumireProducator;
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

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public int getStoc() {
		return stoc;
	}

	public void setStoc(int stoc) {
		this.stoc = stoc;
	}

	public String getDenumireTip() {
		return denumireTip;
	}

	public void setDenumireTip(String denumireTip) {
		this.denumireTip = denumireTip;
	}

	public String getDenumireProducator() {
		return denumireProducator;
	}

	public void setDenumireProducator(String denumireProducator) {
		this.denumireProducator = denumireProducator;
	}
	
	
	
}
