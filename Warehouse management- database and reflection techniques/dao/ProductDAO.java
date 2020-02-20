package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import connection.ConnectionFactory;
import model.Product;

public class ProductDAO extends AbstractDAO<Product> {
	private List<Product> createObjects(ResultSet resultSet) {
		List<Product> list = new ArrayList<Product>();

		try {
			while (resultSet.next()) {
				Product instance = new Product();
				instance.setIdProduct(resultSet.getInt(1));
				instance.setProductName(resultSet.getString(2));
				instance.setQuantity(resultSet.getInt(3));
				instance.setPrice(resultSet.getInt(4));
				list.add(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Product findByName(String name) {
		Product toReturn = null;
		String findStatementString = "SELECT * FROM Product where name = ?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();
			int id = rs.getInt("idProduct");
			int quantity = rs.getInt("quantity");
			int price = rs.getInt("price");
			toReturn = new Product(id, name, quantity, price);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public List<Product> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM product;";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public List<Product> findAllByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = "SELECT idProduct FROM product WHERE name=?";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public int insert(Product product) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer id = -1;
		String query = "INSERT INTO product ( name, quantity, price ) VALUES ( ?, ?, ? );";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getQuantity());
			statement.setInt(3, product.getPrice());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			id = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return id;
	}

	public boolean updateProduct(String name, Product product) {
		String updateStatementString = "UPDATE product SET quantity=?,price=? WHERE name=?";
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, product.getQuantity());
			updateStatement.setString(3, product.getProductName());
			updateStatement.setInt(2, product.getPrice());
			updateStatement.execute();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return false;
	}

	public boolean updateProductQuantity(String name, Product product, int quantity) {
		String updateStatementString = "UPDATE product SET quantity=? WHERE name=?";
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, quantity);
			updateStatement.setString(2, product.getProductName());
			updateStatement.execute();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:updateQuantity " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return false;
	}
}
