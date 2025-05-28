package com.fisnig.springBootCrud.repository;

import com.fisnig.springBootCrud.model.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	
	@Query("SELECT p FROM Customer p WHERE CONCAT(p.firstName, ' ', p.lastName, ' ', p.phone, ' ', p.course) LIKE %?1%")   
	 public List<Customer> search(String keyword);
	}

