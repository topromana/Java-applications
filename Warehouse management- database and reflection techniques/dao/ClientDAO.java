package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import connection.ConnectionFactory;
import model.Client;
import model.Product;

public class ClientDAO extends AbstractDAO<Client> {
	
	
	public static Client findByName(String name) {
		Client toReturn = null;
	    String findStatementString = "SELECT * FROM Client where name = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("idClient");
			//String name = rs.getString("name");
			String address = rs.getString("address");
			//int price = rs.getInt("price");
			toReturn = new Client(id, name, address);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

}
