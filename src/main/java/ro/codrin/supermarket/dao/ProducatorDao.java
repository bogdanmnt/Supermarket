package ro.codrin.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.codrin.supermarket.entitati.Producator;

public class ProducatorDao {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Driverul conectorului de MySQL
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/supermarket";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	Connection conn = null;
	Statement stmt = null;
	
	
	// Metoda cu mari probleme de securitate - nu folosim aici PreparedStatement
	// Si cu muuuuuuult cod....
	public void adaugaProducator(String denumire, String adresa) {
		try {
			// STEP 0: Incarcam driver JDBC de la MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// PASUL 1: crearea unei conexiuni cu baza de date
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// PASUL 2: cream statementul
			// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...');
			String sql = "INSERT INTO producator (denumire, adresa) VALUES ('" + denumire + "', '" + adresa + "');";
			stmt = conn.createStatement();
			
			// PASUL 3: executam statementul
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			// inchidem resursele in blocul finally pentru ca acesta 
			// se executa indiferent daca avem exceptie sau nu
			
			try {
				stmt.close();
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
	
	
	// si mult cod...
	public List<Producator> getProducatori(){
				
		// SELECT * FROM producator;
		try {
			// STEP 0: Incarcam driver JDBC de la MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// PASUL 1: crearea unei conexiuni cu baza de date
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// PASUL 2: cream statementul
			// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...')
			String sql = "SELECT * FROM producator;";
			stmt = conn.createStatement();
			
			// PASUL 3: executam statementul
			ResultSet rs = stmt.executeQuery(sql);
			// rs este un fel de array de 5 producatori in cazul nostru.
			ArrayList<Producator> producatori = new ArrayList<>();
			while(rs.next()) {
				int id = rs.getInt("producator_id");
				String denumire = rs.getString("denumire");
				String adresa = rs.getString("adresa");
				producatori.add(new Producator(id, denumire, adresa));
			}
			return producatori;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			// inchidem resursele in blocul finally pentru ca acesta 
			// se executa indiferent daca avem exceptie sau nu
			
			try {
				stmt.close();
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
	
	// problema grava de securitate.
	// si mult cod...
	public List<Producator> getProducator(int id){
				
		// SELECT * FROM producator;
		try {
			// STEP 0: Incarcam driver JDBC de la MySQL
			Class.forName("com.mysql.jdbc.Driver");

			// PASUL 1: crearea unei conexiuni cu baza de date
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// PASUL 2: cream statementul
			// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...')
			String sql = "SELECT * FROM producator where producator_id = " + id + ";";
			
			
			System.out.println(sql);
			
			stmt = conn.createStatement();
			
			// PASUL 3: executam statementul
			ResultSet rs = stmt.executeQuery(sql);
			// rs este un fel de array de 5 producatori in cazul nostru.
			ArrayList<Producator> producatori = new ArrayList<>();
			while(rs.next()) {
				int producatorId = rs.getInt("producator_id");
				String denumire = rs.getString("denumire");
				String adresa = rs.getString("adresa");
				producatori.add(new Producator(producatorId, denumire, adresa));
			}
			return producatori;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			// inchidem resursele in blocul finally pentru ca acesta 
			// se executa indiferent daca avem exceptie sau nu
			
			try {
				stmt.close();
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





