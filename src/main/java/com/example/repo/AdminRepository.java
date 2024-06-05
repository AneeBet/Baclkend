package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Admin;


public interface AdminRepository extends JpaRepository <Admin, Integer>{
	//@Query(value = "select * from team t where t.name = ?",nativeQuery = true) performance is well for this
	//@Query(value = "select t from Team t where t.name = ?1") // JPQL 'T'  of team is important as name of class come, t.attribute name should be written exactly
//	public List<Customer> findByName(String name);
//	
//	@Query(value = "select * from team t where t.name = ?1 and t.coach = ?2",nativeQuery = true)
//	public List<Customer> findByNameAndCoach(String name, String coach);
	

	
}
