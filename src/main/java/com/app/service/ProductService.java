package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.persistence.dao.ProductRepository;
import com.app.persistence.model.Product;

@Service
public class ProductService {
	
	//dependency injection using similar to @inject & @EJB .. in java ee
	@Autowired
	private ProductRepository productRepository;
	
	public void addProduct(String name, double price)
	{
		Product p = new Product(name, price);
		// persist the product 
		productRepository.save(p);
	}
	

}
