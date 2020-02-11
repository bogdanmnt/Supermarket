package ro.codrin.supermarket.entitati;

public class Producator {
	    private int id;
	    private String denumire;
	    private String adresa;
		
		public Producator(int id, String denumire, String adresa){
			this.id=id;
			this.denumire=denumire;
			this.adresa=adresa;
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

		public String getAdresa() {
			return adresa;
		}

		public void setAdresa(String adresa) {
			this.adresa = adresa;
		}
		
		
}
