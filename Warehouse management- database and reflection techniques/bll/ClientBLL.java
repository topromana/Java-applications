package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import bll.validators.Validator;
import dao.AbstractDAO;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {
	private List<Validator<Client>> validators;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();

	}

	public Client findByName(String name) {
		ClientDAO c = new ClientDAO();
		Client client = ClientDAO.findByName(name);
		return client;
	}

	public int insertClient(Client client) throws Exception {
		if (!client.valid()) {
			throw new Exception("please complete all the fields of the client except for id");
		}
		AbstractDAO<Client> a = new ClientDAO();
		int cl = a.insert(client);
		if (cl == 0) {
			throw new NoSuchElementException("The client" + client + "can't be inserted!");
		}
		return cl;
	}

	public boolean deleteClient(Client client, String name) {
		AbstractDAO<Client> c = new ClientDAO();
		c.delete(client, name);
		return true;
	}

	public boolean updateClient(Client client, String name) {
		AbstractDAO<Client> c = new ClientDAO();
		c.update(name, client);
		return true;
	}

	public List<Client> getAllClients() {
		ClientDAO c = new ClientDAO();
		List<Client> clientList = c.findAll();
		return clientList;

	}

	public List<Client> getAllClientNames() {
		ClientDAO c = new ClientDAO();
		List<Client> names = c.findAllNames();
		return names;

	}

}
