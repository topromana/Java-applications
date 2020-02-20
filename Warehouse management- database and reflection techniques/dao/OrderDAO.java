package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Order;
import model.Product;

public class OrderDAO {

	private List<Order> createObjects(ResultSet resultSet) {
		List<Order> list = new ArrayList<Order>();

		try {
			while (resultSet.next()) {
				Order instance = new Order();
				instance.setIdOrder(resultSet.getInt(1));
				instance.setIdProduct(resultSet.getInt(2));
				instance.setIdClient(resultSet.getInt(3));
				instance.setQuantity(resultSet.getInt(4));
				list.add(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Order> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		// String query = "SELECT * FROM order;";
		String query = "SELECT * FROM warehouse.`order`";
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

	public int insert(Order order) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer id = -1;
		String query = "INSERT INTO `warehouse`.`order` ( `idProduct`, `idClient`, `quantity`) VALUES (?, ?, ?);";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, order.getIdProduct());
			statement.setInt(2, order.getIdClient());
			statement.setInt(3, order.getQuantity());
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

}
