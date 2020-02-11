package ro.codrin.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.codrin.supermarket.entitati.Factura;
import ro.codrin.supermarket.entitati.Tip;

public class FacturaDao {

	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Driverul conectorului de MySQL
		static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/supermarket";

		// Database credentials
		static final String USER = "root";
		static final String PASS = "";

		Connection conn = null;
		PreparedStatement ps = null;
		
		
		
		public void adaugaFactura(int supermarketId) {
			try {
				
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				
				
				// PASUL 2: cream statementul
				String sql = "INSERT INTO factura (supermarket_id) VALUES (?);";
				ps = conn.prepareStatement(sql); // aici se compileaza query-ul
				// Abia apoi se adauga parametrii
				ps.setInt(1, supermarketId);
				
				// PASUL 3: executam statementul
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
		
		public List<Factura> getFacturi(){
			
			// SELECT * FROM producator;
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...')
				String sql = "SELECT f.factura_cod as factura_cod, f.data_facturare as data_facturare, f.pret_total as pret_total, s.locatie as locatieSupermarket "
						   + "FROM factura f "
						   + "JOIN supermarket s USING (supermarket_id);";
				ps = conn.prepareStatement(sql);
				
				// PASUL 3: executam statementul
				ResultSet rs = ps.executeQuery();
				// rs este un fel de array de 5 producatori in cazul nostru.
				ArrayList<Factura> facturi = new ArrayList<>();
				while(rs.next()) {
					String cod = rs.getString("factura_cod");
					String dataFacturare = rs.getString("data_facturare");
					double pretTotal = rs.getDouble("pret_total");
					String denumireSupermarket = rs.getString("locatieSuperMarket");
					facturi.add(new Factura(cod, dataFacturare,pretTotal, denumireSupermarket));
				}
				return facturi;
				
				
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
		
		public int getCodFacturaDeLaUltimaFactura() {
			try {
				
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				
				// PASUL 2: cream statementul
				String sql0	= "SELECT * FROM factura ORDER BY factura_cod DESC LIMIT 1";
				ps = conn.prepareStatement(sql0);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					return rs.getInt("factura_cod");
				}
				
			

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
			return -1;
		}
		
		public void setPretTotal(Integer id, Double pretTotal) {
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...');
				String sql = "UPDATE factura SET pret_total = ? WHERE factura_cod = ?";
				ps = conn.prepareStatement(sql); // aici se compileaza query-ul
				// Abia apoi se adauga parametrii
				ps.setDouble(1, pretTotal);
				ps.setInt(2, id);
				
				// PASUL 3: executam statementul
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
		
		
}
