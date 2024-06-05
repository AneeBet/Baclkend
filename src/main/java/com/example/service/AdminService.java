package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Admin;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.AdminRepository;
import com.example.repo.CustomerRepository;

@Component
public class AdminService {

	@Autowired
	CustomerRepository cr;
	
	
	public Optional<Customer> getCustomer(int id) {;
	return cr.findById(id);
	}
	

	public List<Customer> getAllCustomer(){
		return cr.findAll();
		}



	public void deleteCustomer(int id) {
		Optional<Customer> cust = cr.findById(id);
		cr.deleteById(id);
	
	}
	
	//send

	


	public Customer addCustomer(Customer custNew) {
		Customer cust = new Customer();
		cust.setAddress(custNew.getAddress());
		cust.setAge(custNew.getAge());
		cust.setBalance(custNew.getBalance());
		cust.setEmail(custNew.getEmail());
		cust.setName(custNew.getName());
		cust.setPassword(custNew.getPassword());
		cust.setPhone(custNew.getPhone());
		cust.setSSN(custNew.getSSN());
		cust.setUsername(custNew.getUsername());
		
		return cr.save(cust);
		
	}
	
	public int login( Login l) {
		String uname = l.getUname();
		String password = l.getPassword();
		
		int id = cr.getByLogin(uname,password);
		return id;
	}
	

	
	
}
