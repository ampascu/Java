package eu.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgMain {

	// Statement: sql statements without parameters
	// PreparedStatement: sql statements with parameters

	// execute: for results that return more than one result set;
	// get the resultsSet with stmt.getResultSet()
	// executeQuery: for results returning only one ResultSet
	// executeUpdate: for INSERT / DELETE / UPDATE
	public static void main(String[] args) {
		Connection c;
		try {
			// show this in system explorer
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			createDb(c);
			insertProduct(c, 1, "Cola", 5.99f);
			insertProduct(c, 2, "Lays", 6.99f);
			
			selectProducts(c);
			insertProduct(c, 3, "Lays2'); DELETE FROM PRODUCT "
					+ "WHERE id IN (1,'", 6.99f);
			
			safeInsertProduct(c, 4, "Lays2'); DELETE FROM PRODUCT "
					+ "WHERE id IN (1,'", 6.99f);
			
			updateProduct(c, 2, "Chio");
			selectProducts(c);
			deleteProduct(c, 1);
			selectProducts(c);
			c.close();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	public static void createDb(Connection c) throws SQLException {
		String toExecute = "CREATE TABLE PRODUCT (ID INT PRIMARY KEY, "
				+ "NAME CHAR(50), PRICE REAL)";
		
		Statement st = c.createStatement();
		st.executeUpdate(toExecute);
		st.close();
	}

	public static void insertProduct(Connection c, int id, String name,
			float price) throws SQLException {
		String toExecute = "INSERT INTO PRODUCT(id, price, name) "
				+ "VALUES ("+ id + ", " + price + ", '"+ name + "')";
		
		Statement st = c.createStatement();
		st.executeUpdate(toExecute);
		st.close();
	}
	
	public static void safeInsertProduct(Connection c, int id, String name,
			float price) throws SQLException {
		String toExecute = "INSERT INTO PRODUCT (id, price, name)"
				+ " VALUES (?,?,?)";
		
		PreparedStatement ps = c.prepareStatement(toExecute);
		ps.setInt(1, id);
		ps.setFloat(2, price);
		ps.setString(3, name);
		
		ps.executeUpdate();
		
		ps.close();
	}

	public static void selectProducts(Connection c) throws SQLException {
		String toExecute = "SELECT * FROM PRODUCT";
		Statement st = c.createStatement();
		ResultSet result = st.executeQuery(toExecute);
		
		while(result.next()) {
			System.out.println("ID: " + result.getInt("id"));
			System.out.println("NAME: " + result.getString("name"));
			System.out.println("PRICE: " + result.getFloat("price") + "\n");
		}
		st.close();
	}

	public static void updateProduct(Connection c, int id, String name) throws SQLException {
		String toExecute = "UPDATE PRODUCT SET name=? WHERE id=?";
		PreparedStatement ps = c.prepareStatement(toExecute);
		
		ps.setString(1, name);
		ps.setInt(2, id);
		
		ps.executeUpdate();
		
		ps.close();
	}

	public static void deleteProduct(Connection c, int id) throws SQLException {
		String toExecute = "DELETE FROM PRODUCT WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(toExecute);
		
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}

}
