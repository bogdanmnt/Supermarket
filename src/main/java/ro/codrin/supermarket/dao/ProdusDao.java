package ro.codrin.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ro.codrin.supermarket.entitati.Produs;

public class ProdusDao {
	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Driverul conectorului de MySQL
		static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/supermarket";

		// Database credentials
		static final String USER = "root";
		static final String PASS = "";

		Connection conn = null;
		PreparedStatement ps = null;
		
		
		
		public void adaugaProdus(String denumire, String pret, String stoc, String tipIdString, String producatorIdString) {
			try {
				
				int tipId = Integer.parseInt(tipIdString);	
				int producatorId = Integer.parseInt(producatorIdString);
				
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				String sql = "INSERT INTO produs (denumire, pret, stoc, tip_id, producator_id) VALUES (?, ?, ?, ?, ?);";
				ps = conn.prepareStatement(sql); // aici se compileaza query-ul
				// Abia apoi se adauga parametrii
				ps.setString(1, denumire);
				ps.setString(2, pret);
				ps.setString(3, stoc);
				ps.setInt(4, tipId);
				ps.setInt(5, producatorId);
				
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
		
		/**
		 * 
		 * @param productIds
		 * 		Daca productIds e null, returneaza detaliile tuturor produselor
		 *      Daca e diferit de null, returneaza doar detaliile produselor respective
		 * @return
		 */
		public List<Produs> getProduse(Set<Integer> productIds){
			
			// SELECT * FROM producator;
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				// INSERT INTO producator (denumire, adresa) VALUES ('Danone', 'strada...')
				String sql = null;
				
				if (productIds == null) {
					// Acum obtinem toate produsele din tabela
					sql = "SELECT p.produs_id as produs_id, p.denumire as denumire, p.pret as pret, p.stoc as stoc, "
						   + "t.denumire as denumireTip, pr.denumire as denumireProducator "
						   + "FROM produs p "
						   + "JOIN tip t USING (tip_id) "
						   + "JOIN producator pr USING (producator_id);";
					
					ps = conn.prepareStatement(sql);
					
				} else {
					
					// Aici obtinem numai detaiile produselor din cos, 
					// pentru ca trebuie calculam pretul total.
					//  SELECT Id, CompanyName, City, Country
				    //  FROM Supplier
				    //  WHERE Country IN ('USA', 'UK', 'Japan')
					
					StringBuilder sb  = new StringBuilder("SELECT p.produs_id as produs_id, p.denumire as denumire, p.pret as pret, p.stoc as stoc, "
							   + "t.denumire as denumireTip, pr.denumire as denumireProducator "
							   + "FROM produs p "
							   + "JOIN tip t USING (tip_id) "
							   + "JOIN producator pr USING (producator_id) "
							   + "WHERE p.produs_id  IN ( ");				
					
					for(int i = 0; i<productIds.size() - 1; i++) {
						sb.append("?, ");
					}
					sb.append("?); ");
					
					ps = conn.prepareStatement(sb.toString());
					
					int j = 1;
					for (int productId : productIds) {
						ps.setInt(j, productId);
						j++;
					}
				}
				
				// PASUL 3: executam statementul
				ResultSet rs = ps.executeQuery();
				// rs este un fel de array de 5 producatori in cazul nostru.
				ArrayList<Produs> produse = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("produs_id");
					String denumire = rs.getString("denumire");
					double pret = rs.getDouble("pret");
					int stoc = rs.getInt("stoc");
					String denumireTip = rs.getString("denumireTip");
					String denumireProducator = rs.getString("denumireProducator");
					produse.add(new Produs(id,denumire,pret,stoc,denumireTip,denumireProducator));
				}
				return produse;
				
				
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
		
		public boolean verifyThatStocIsGreaterThanZero(int produsId) {
			// SELECT stoc FROM produs WHERE produs_id = ...;
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				String sql = "SELECT stoc FROM produs WHERE produs_id = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, produsId);
				
				// PASUL 3: executam statementul
				ResultSet rs = ps.executeQuery();
				// rs este un fel de array de 5 producatori in cazul nostru.
				int id = -1;
				while(rs.next()) {
					id = rs.getInt("stoc");
				}
				return id > 0 ? true : false;
				
				
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
			return false;
		}

		public Integer getStoc(Integer produsId) {
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				String sql = "SELECT stoc FROM produs WHERE produs_id = ?";
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, produsId);
				
				// PASUL 3: executam statementul
				ResultSet rs = ps.executeQuery();        
				// ResultSet se aseamana aici cu un array de inturi.
				// Dar pentru ca avem clauza "WHERE produs_id = ?", arrayul va contine un singur element.
				int stoc = -1;
				if(rs.next()) {
					stoc = rs.getInt("stoc");
				}
				return stoc;
				
				
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
		
		public void setStocNou(Integer id, Integer stoc) {
			try {
				// STEP 0: Incarcam driver JDBC de la MySQL
				Class.forName("com.mysql.jdbc.Driver");

				// PASUL 1: crearea unei conexiuni cu baza de date
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// PASUL 2: cream statementul
				String sql = "UPDATE produs SET stoc = ? WHERE produs_id = ?";
				ps = conn.prepareStatement(sql); // aici se compileaza query-ul
				// Abia apoi se adauga parametrii
				ps.setInt(1, stoc);
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
