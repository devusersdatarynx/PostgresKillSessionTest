package com.postgreskillsession.test.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.postgreskillsession.test.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
List<Customer> findByFirstName(String FirstName);

List<Customer> findAll();

@Procedure(name = "insert_customer")
public void insert_customer(int id, String firstname, String lastname);

@Procedure(name = "update_customer")
void update_customer(int id, String firstname, String lastname);

@Procedure(name = "getAllCustomer")
List<Customer> getAll();

@Query(value = "SELECT CONNECTION_ID()", nativeQuery = true)
public Long getSession();

}