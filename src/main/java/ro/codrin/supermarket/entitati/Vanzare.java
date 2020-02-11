package ro.codrin.supermarket.entitati;

public class Vanzare {
	int idProdus;
	String denumireClient;
	String codFactura;
	int cantitate;
	
	public Vanzare(int idProdus, String denumireClient, String codFactura, int cantitate) {
		super();
		this.idProdus = idProdus;
		this.denumireClient = denumireClient;
		this.codFactura = codFactura;
		this.cantitate = cantitate;
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public String getDenumireClient() {
		return denumireClient;
	}

	public void setDenumireClient(String denumireClient) {
		this.denumireClient = denumireClient;
	}

	public String getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(String codFactura) {
		this.codFactura = codFactura;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	
	
	
	
	
}
