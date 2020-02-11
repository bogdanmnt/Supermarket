package ro.codrin.supermarket.entitati;

public class Factura {
	
	private String cod;
	String dataFacturare;
	double pretTotal;
	String denumireSupermarket;
	
	public Factura(String cod, String dataFacturare, double pretTotal, String denumireSupermarket) {
		super();
		this.cod = cod;
		this.dataFacturare = dataFacturare;
		this.pretTotal = pretTotal;
		this.denumireSupermarket = denumireSupermarket;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDataFacturare() {
		return dataFacturare;
	}

	public void setDataFacturare(String dataFacturare) {
		this.dataFacturare = dataFacturare;
	}

	public double getPretTotal() {
		return pretTotal;
	}

	public void setPretTotal(double pretTotal) {
		this.pretTotal = pretTotal;
	}

	public String getDenumireSupermarket() {
		return denumireSupermarket;
	}

	public void setDenumireSupermarket(String denumireSupermarket) {
		this.denumireSupermarket = denumireSupermarket;
	}
	
	
	
}
