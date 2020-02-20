package start;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.RestaurantSerializator;
import models.BaseProduct;
import models.MenuItem;
import models.Order;
import models.Restaurant;
import presentation.AdministratorGUI;
import presentation.WaiterGUI;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		
		Restaurant rest= RestaurantSerializator.deserialization();
		AdministratorGUI adminFrame = new AdministratorGUI(rest);
		RestaurantSerializator r = new RestaurantSerializator();
		Order o = new Order(1,"01.02.2003",2);
		
		
		MenuItem obj1 = new BaseProduct("cola",7.0);
		MenuItem obj2 = new BaseProduct("fanta",10.0);
		MenuItem obj3 = null;
		MenuItem obj4 = null;
		Set<MenuItem> newProductsList = new HashSet<MenuItem>();
		newProductsList.add(obj1);
		List<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(obj1);
		menu.add(obj2);
		List <MenuItem> menuReceived = new ArrayList<MenuItem>();
		
	WaiterGUI waiterFrame = new WaiterGUI(rest);
		
	}

}
