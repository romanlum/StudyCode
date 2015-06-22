package at.lumetsnet.caas.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import at.lumetsnet.caas.dal.DataAccessException;

public class GenericDaoTest {

	protected static final String CONNECTION_STRING = "jdbc:mysql://localhost/CaasDb";
	protected static final String USER_NAME = "root";
	protected static final String PASSWORD = null;
	
	
	public void cleanTable(String table) {
		try(Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD)){
			//clean table
			try(PreparedStatement stmt =  con.prepareStatement(String.format("Delete from %s", table))) {
				stmt.executeUpdate();
			}
		} catch(SQLException ex) {
			throw new DataAccessException(ex.getMessage());
		}
	}
}
