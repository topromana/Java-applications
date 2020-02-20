package start;

import presentation.ClientOperations;
import presentation.PlaceOrderWindow;
import presentation.ProductOperationsWindow;

public class Main {

	public static void main(String[] args) {
		new ClientOperations();
		new PlaceOrderWindow();
		new ProductOperationsWindow("produse");
	}

}
