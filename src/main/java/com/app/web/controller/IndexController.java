package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.service.ProductService;

@Controller
public class IndexController {
	
	
	// dependency injection 
	@Autowired
	private ProductService productService;
	
	@GetMapping("/index")
	public String showIndex(Model model)
	{
		String message = "hello world !";
		model.addAttribute("message", message);
		return "index";
	}
	@GetMapping("/addProduit")
	public String showAddProduitView()
	{
		return "addProduit";
	}
	
	@PostMapping("/addProduit")
	public String addProduit(@RequestParam("name") String name,@RequestParam("price") double price,Model model)
	{
		String message = "product added successfully ! ";
		productService.addProduct(name, price);
		model.addAttribute("message", message);
		return "addProduit";
	}

}
