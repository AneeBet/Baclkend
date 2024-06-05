package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Admin;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.AdminRepository;
import com.example.service.AdminService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService as;
    //read 
	@PostMapping("/login")
	public int login(@RequestBody Login L) {
	return as.login(L);
	}
	//Done
	@GetMapping("/getcustomer/{id}")          
	public Optional<Customer> getCustomer (@PathVariable int id)  throws ResourceNotFoundException{
		Optional<Customer> cust  = Optional.ofNullable(as.getCustomer(id).orElseThrow(()->new ResourceNotFoundException("Customer id not found "+ id)) );  
	return cust;
	}
	
	@GetMapping("/getallcustomer")          
	public List<Customer> getAllCustomer() {;
	return as.getAllCustomer();
	}
	
	@PostMapping("/addcustomer")
	public  Customer addCustomer(@RequestBody Customer custNew){
		return as.addCustomer(custNew);
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	public void deleteCustomer(@PathVariable int id) throws ResourceNotFoundException{
		//return Optional.ofNullable(as.deleteCustomer(id).orElseThrow(()->new ResourceNotFoundException("Customer id not found "+ id)) );  
		as.deleteCustomer(id);
	}
	
	@PutMapping("/updatecustomer/{id}")
	public Customer updateCustomer(@PathVariable int id,@RequestBody Customer custNew) throws ResourceNotFoundException {
		 Optional.ofNullable(as.getCustomer(id).orElseThrow(()->new ResourceNotFoundException("Customer id not found "+ id)) );
	return as.updateCustomer(id, custNew);
	}
	
	

	
}



