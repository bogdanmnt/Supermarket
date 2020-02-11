package ro.codrin.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.codrin.supermarket.entitati.Client;
import ro.codrin.supermarket.entitati.Raion;

public class ClientDao {

	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Driverul conectorului de MySQL
		static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/supermarket";

		// Database credentials
		static final String USER = "root";
		static final String PASS = "";

		Connection conn = null;
		PreparedStatement ps = null;
		
		
		
		public void adaugaClient(String nume, String prenume) {
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...');
				String sql = "INSERT INTO client (nume,prenume) VALUES (?, ?);";
				ps = conn.prepareStatement(sql); // aici se compileaza query-ul
				// Abia apoi se adauga parametrii
				ps.setString(1, nume);
				ps.setString(2, prenume);
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
		
		public List<Client> getClienti(){
			
			// SELECT * FROM producator;
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...')
				String sql = "SELECT * FROM client;";
				ps = conn.prepareStatement(sql);
				
				// PASUL 3: executam statementul
				ResultSet rs = ps.executeQuery();
				// rs este un fel de array de 5 producatori in cazul nostru.
				ArrayList<Client> clienti = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("client_id");
					String nume = rs.getString("nume");
					String prenume = rs.getString("prenume");
					clienti.add(new Client(id,nume,prenume));
				}
				return clienti;
				
				
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
