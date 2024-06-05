package com.example.model;



import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	@Id  //primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	 @Pattern(regexp = "[a-zA-Z]+")
	 @Size(min = 2, max = 50)
	 @NotNull
	private String Name;
	 
	 @NotNull
	 @Size(min = 2, max = 50)
	 @Pattern(regexp = "\\w+") 
	private String Username;
	
	 @NotNull
	 @Size(min = 8, max = 20)
	 @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
	private String Password;
	
	 @Positive
	 @Max(120)
	private int Age;
	
	
	private String SSN;
	
	  @NotBlank
	   @Size(min = 5, max = 100, message = "Address must be between {min} and {max} characters long")
	private String Address;
	
	  @jakarta.validation.constraints.Email
	private String Email;
	
	  @Pattern(regexp = "^\\d{10}$")
	private String Phone;
	
	  @Positive
	private float Balance;
	
//		@OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
//		@OnDelete(action = OnDeleteAction.CASCADE)
//		@JsonIgnore
//		private List<Transaction> transactions = new ArrayList<>();


}
