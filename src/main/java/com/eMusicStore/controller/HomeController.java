package com.eMusicStore.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eMusicStore.daoInter.ProductInter;
import com.eMusicStore.model.Product;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@Autowired
	private ProductInter productdao;

	@RequestMapping("/productList")
	public String getProducts(Model model) {

		model.addAttribute("products", productdao.getAllProduct());

		return "productList";

	}

	@RequestMapping("/productlist/viewProduct/{productId}")
	public String getviewProducts(@PathVariable int productId, Model model) throws IOException {

		model.addAttribute("product", productdao.getProductById(productId));
		return "viewProducts";

	}

	@RequestMapping("/admin")
	public String adminPage()
	{
	System.out.println("inside admin");
	return "admin";
	}
	@RequestMapping("/admin/productInventory")
	public String adminProductInventory(Model model)
	{
	System.out.println("inside inventory");
	model.addAttribute("products",productdao.getAllProduct());

	return "productInventory";
	}
	@RequestMapping("/admin/productInventory/addProduct" )
	public String addProduct(Model model)
	{
	Product product=new Product();
	product.setProductCategory("instrument");
	product.setProductCondition("new");
	product.setProductStatus("active");
	System.out.println("inside inventory");
	model.addAttribute("product",product);
	return "addProduct";
	}
	@RequestMapping(value="/admin/productInventory/addProduct", method=RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product")Product product){
	productdao.addProduct(product);
	return "redirect:/admin/productInventory";
		
	}
	@RequestMapping("/admin/productInventory/deleteProduct{id}")
	public String deleteProduct(@PathVariable int id, Model model) {

		productdao.deleteProduct(id);
		
		return "redirect:/admin/productInventory";

	}
		
		
	}
	
	
	
	
	
	
	
	
	

