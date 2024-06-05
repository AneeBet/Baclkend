package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.AdminController;
import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.CustomerRepository;
import com.example.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private AdminService as;
	
	@MockBean
	private CustomerRepository cr;
	
	@InjectMocks
	private AdminController ac;
	
	
	private Customer cust1;
	
	private Login login;
	
	
	@BeforeEach
	void setUp()
	{
		cust1=new Customer();
		cust1.setAddress("Bangalore");
		cust1.setAge(25);
		cust1.setBalance(10000);
		cust1.setEmail("anurag@brillio.com");
		cust1.setId(1);
		cust1.setName("Anurag");
		cust1.setPassword("#Anurag8755");
		cust1.setPhone("8755971165");
		cust1.setSSN("1234567890");
		cust1.setUsername("AneeBet");
	    
	    login = new Login();
	    login.setPassword("#Anu8755");
	    login.setUname("Anurag");
	}
	
	@Test
	void testRead() throws Exception{
		
		List<Customer> custs=Arrays.asList(cust1);
		when(as.getAllCustomer()).thenReturn(custs);
		
		mockMvc.perform(get("/admin/getallcustomer"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].username").value("AneeBet"));
						
	
	}
	

	
	@Test
	void testReadOne() throws Exception {
		
		when(as.getCustomer(1)).thenReturn(Optional.of(cust1));
		

		mockMvc.perform(get("/admin/getcustomer/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value("Anurag"));
			
	}

	@Test
	void testAdd() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String teamJson = objectMapper.writeValueAsString(cust1); 
		

		mockMvc.perform(post("/admin/addcustomer")
		.contentType(MediaType.APPLICATION_JSON)
		.content(teamJson))
		.andExpect(status().isOk());
		
		verify(as).addCustomer(cust1);
		
			
	}
	
	
	@Test
	void testDelete() throws Exception {
	
      

		mockMvc.perform(delete("/admin/deletecustomer/1"))
		.andExpect(status().isOk());
		
		verify(as).deleteCustomer(1);
		
		
		
	
		
			
	}
	
	
	
	
	
	
}
