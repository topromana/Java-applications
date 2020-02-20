package dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public abstract class AbstractDAO<T> {

	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	private String createSelectStarQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	private String createSelectNameQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" name ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	public List<T> findAll() {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectStarQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public List<T> findAllNames() {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectNameQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String tableColumns(boolean placeHolders) {
		StringBuilder str = new StringBuilder();
		boolean first = true;
		int count = 0;
		for (Field f : type.getDeclaredFields()) {
			if (count != 0) {
				if (first) first = false;
				else str.append(", ");
				if (placeHolders) str.append("?");
				else str.append(f.getName());
			}
			count++;
		}
		return str.toString();
	}

	public int insert(T t) throws SQLException, InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
		System.out.println("intrare insertion");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer id = 0;
		try {
			String query = "INSERT INTO " + type.getSimpleName() + "(" + tableColumns(false) + ")" + " VALUES ("+ tableColumns(true) + ")";
			System.out.println(query);
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			for (Field field : type.getDeclaredFields()) {
				if (i != 0) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getReadMethod();
					Object value = method.invoke(t);
					statement.setObject(i, value);
					System.out.println("mijloc" + i);
				}
				i++;}
			statement.addBatch();
			statement.executeBatch();
resultSet = statement.getGeneratedKeys();
			resultSet.next();
			id = resultSet.getInt(1);
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return id;
	}

	private static boolean number(String s) {
		try {
		Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private String updateSets(String name, T t) {
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		for (Field f : type.getDeclaredFields()) {
			if (first) first = false;
			else sb.append(", ");
			sb.append(f.getName());
			sb.append(" = ");
			PropertyDescriptor propertyDescriptor;
			try {
				propertyDescriptor = new PropertyDescriptor(f.getName(), type);
				Method method = propertyDescriptor.getReadMethod();
				Object value = method.invoke(t);
				if (number(value.toString())) sb.append(value);
				else if (value.toString() == "false") sb.append('0');
				else if (value.toString() == "true")sb.append('1');
				else 
				{sb.append("'");
				sb.append(value);
				sb.append("'");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return sb.toString();

	}

	public void update(String name, T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement("UPDATE " + type.getSimpleName() + " SET "+ updateSets(name, t) + " WHERE name=?");
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	public void delete(T t, String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement("DELETE FROM " + type.getSimpleName() + " WHERE name=?");
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

}
