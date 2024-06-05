package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.model.Customer;
import com.example.model.Login;
import com.example.repo.CustomerRepository;
import com.example.service.AdminService;




@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
	@Mock
	private CustomerRepository cr;
	
	@InjectMocks
	private AdminService as;
	
	@MockBean
	private Customer cust1;
	@MockBean
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
	void  testRead()
	{
		List<Customer> custs=Arrays.asList(cust1);
		when(cr.findAll()).thenReturn(custs);
		
		List<Customer> result=as.getAllCustomer();
		assertEquals(1,result.size());
		assertEquals("Anurag",result.get(0).getName());
		
		
	}
	@Test
	void testReadOne() {
		when(cr.findById(1)).thenReturn(Optional.of(cust1));
		
		Optional<Customer>result=as.getCustomer(1);
		assertTrue(result.isPresent());
		assertEquals("Anurag",result.get().getName());
	}
	@Test
	void testAdd() {
		
		when(cr.save(any(Customer.class))).thenReturn(cust1);
		 
        Customer result = as.addCustomer(cust1);
        assertNotNull(result);
        assertEquals("Anurag", result.getName());
		
	}

	@Test
	void testDelete()
	{
		as.deleteCustomer(1);
		
		verify(cr).deleteById(1);
		
	}
	
	@Test
	void testLogin()
	{
		as.login(login);
		
		verify(cr).getByLogin(login.getUname(),login.getPassword());
		
	}
	
 
}