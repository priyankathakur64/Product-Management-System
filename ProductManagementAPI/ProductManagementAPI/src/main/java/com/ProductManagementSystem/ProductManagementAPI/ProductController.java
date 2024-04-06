package com.ProductManagementSystem.ProductManagementAPI;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	SessionFactory sf;
	
	//Create a new product
	@PostMapping("add/product")
	public String create(@RequestBody Product product){
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.save(product);
		t.commit();
		s.close();
		return "New product created "+product;
	}
	
	//Get a list of all products.
	@GetMapping("showAll")
	public String getAll(){
		Session s = sf.openSession();
		Query query = s.createQuery("from Product");
		List<Product> list = query.list();
		s.close();
		return "Show all products "+list;
	}
	
	//Get details of a specific product/{id}
	@GetMapping("showSingle/{pid}")
	public String get(@PathVariable int pid){
		Session s = sf.openSession();
		Product p = s.get(Product.class, pid);
		s.close();
		return "Show all products "+p;
	}
	//Update an existing product
	@PutMapping("update")
	public String update(@RequestBody Product product){
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		s.update(product);
		t.commit();
		s.close();
		return "Updated product "+product;
	}
	//Delete a product/{id}
	@DeleteMapping("product/{pid}")
	public String delete(@PathVariable int pid){
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		Product p =s.get(Product.class, pid);
		s.delete(p);
		t.commit();
		s.close();
		return "Delete Successful "+pid;
	}
}
