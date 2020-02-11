package ro.codrin.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ro.codrin.supermarket.dto.ProdusDePeFactura;
import ro.codrin.supermarket.entitati.Factura;

public class VanzareDao {

	// JDBC driver name and database URL
			static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Driverul conectorului de MySQL
			static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/supermarket";

			// Database credentials
			static final String USER = "root";
			static final String PASS = "";

			Connection conn = null;
			PreparedStatement ps = null;
			
			
			
	public void adaugaVanzare(HashMap <Integer,Integer> produsIduriSiCantitati, int clientId, int facturaCod) {
		try {
			
			// STEP 0: Incarcam driver JDBC de la MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// PASUL 1: crearea unei conexiuni cu baza de date
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			// PASUL 2: cream statementul
			// INSERT INTO vanzare (produs_id, client_id, factura_cod, cantitate ) VALUES (?,?,?,?), (?,?,?,?), (?,?,?,?), ...
			
			StringBuilder sb	= new StringBuilder("INSERT INTO vanzare (produs_id, client_id, factura_cod, cantitate ) VALUES ");
			
			for(int i = 0; i<produsIduriSiCantitati.size()-1; i++) {
				sb.append("(?,?,?,?), ");
			}
			sb.append("(?,?,?,?) ;");
			
			ps = conn.prepareStatement(sb.toString());
			
			Set<Integer> keySet = produsIduriSiCantitati.keySet();
			
			int i = 0;
			for(Integer key : keySet) {
							
				ps.setInt(i * 4 + 1, key);      //produsId e chiar cheia din HashMap
				ps.setInt(i * 4 + 2, clientId);
				ps.setInt(i * 4 + 3, facturaCod);
				ps.setInt(i * 4 + 4, produsIduriSiCantitati.get(key));   // cantiatea este valoarea din HashMap
				
				i++;
			}
			
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			// inchidem resursele in blocul finally pentru ca acesta 
			// se executa indiferent daca avem exceptie sau nu
			
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// In practica conexiunea nu se inchide, ci se returneaza in pool-ul de conexiuni
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		

	}



	public List<ProdusDePeFactura> getToateVanzarileDeLaOFactura(int codUltimaFactura) {

		try {
			// STEP 0: Incarcam driver JDBC de la MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// PASUL 1: crearea unei conexiuni cu baza de date
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// PASUL 2: cream statementul
			
			String sql = "SELECT p.produs_id as produsId, p.denumire as denumire, p.pret as pretUnitar, "
					   + " prod.denumire as denumireProducator, v.cantitate as cantitate "
					   + " FROM produs p "
					   + " JOIN producator prod USING (producator_id) "
					   + " JOIN vanzare v USING (produs_id) "
					   + " WHERE v.factura_cod = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, codUltimaFactura);
			
			// PASUL 3: executam statementul
			ResultSet rs = ps.executeQuery();
			// rs este un fel de array de 5 producatori in cazul nostru.
			ArrayList<ProdusDePeFactura> produseVandute = new ArrayList<>();
			while(rs.next()) {
				int produsId = rs.getInt("produsId");
				String denumire = rs.getString("denumire");
				double pretUnitar = rs.getDouble("pretUnitar");
				String denumireProducator = rs.getString("denumireProducator");
				int cantitate = rs.getInt("cantitate");
				double pretUnitarOriCantitate = pretUnitar * cantitate;
				produseVandute.add(new ProdusDePeFactura(produsId, denumire, pretUnitar, denumireProducator, cantitate, pretUnitarOriCantitate)) ;
			}
			return produseVandute;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			// inchidem resursele in blocul finally pentru ca acesta 
			// se executa indiferent daca avem exceptie sau nu
			
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			// In practica conexiunea nu se inchide, ci se returneaza in pool-ul de conexiuni
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return null;
		
	}
	
	
}
