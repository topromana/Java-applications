package bll;

import java.util.List;
import java.util.NoSuchElementException;

import dao.AbstractDAO;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;

public class ProductBLL {

	public List<Product> getAllProducts() {
		ProductDAO p = new ProductDAO();
		List<Product> productList = p.findAll();
		return productList;

	}

	public Product findByName(String name) {
		ProductDAO p = new ProductDAO();
		Product product = ProductDAO.findByName(name);
		return product;
	}

	public int insertProduct(Product product) throws Exception {
		if (!product.valid()) {
			throw new Exception("please complete all the fields of the client except for id");
		}
		System.out.println("cacat in insertion");
		AbstractDAO<Product> a = new ProductDAO();
		int cl = a.insert(product);
		if (cl == 0) {
			throw new NoSuchElementException("The client" + product + "can't be inserted!");
		}
		return cl;
	}

	public boolean deleteProduct(Product product, String name) {
		AbstractDAO<Product> p = new ProductDAO();
		p.delete(product, name);
		return true;
	}

	public boolean updateProduct(Product product, String name) {
		ProductDAO p = new ProductDAO();
		return p.updateProduct(name, product);

	}

	public boolean updateProductQuantity(Product product, String name, int quantity) {
		ProductDAO p = new ProductDAO();
		return p.updateProductQuantity(name, product, quantity);

	}
}
