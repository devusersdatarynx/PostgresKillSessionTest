package com.postgreskillsession.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.postgreskillsession.test.entity.Customer;
import com.postgreskillsession.test.entity.SessionDetails;
import com.postgreskillsession.test.respository.CustomerRepository;
import com.postgreskillsession.test.respository.SessionDetailRepository;

@RestController
public class CustomerController {

@Autowired
CustomerRepository repository;

@Autowired
SessionDetailRepository sessionRepository;

@Autowired
JdbcTemplate jdbcTemplate;

@GetMapping("/bulkcreate")
public String bulkcreate(){
repository.save(new Customer("Rajesh", "Bhojwani"));
        repository.saveAll(Arrays.asList(new Customer("Salim", "Khan")
                       , new Customer("Rajesh", "Parihar")
                       , new Customer("Rahul", "Dravid")
                       , new Customer("Dharmendra", "Bhojwani")));
return "Customers are created";
}

@Transactional
@PostMapping("/create")
public String create(@RequestBody Customer customer, @RequestParam Integer pid){
repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
jdbcTemplate.execute("select pg_terminate_backend(pid) \r\n"
		+ "from pg_stat_activity\r\n"
		+ "where pid = pg_backend_pid();");
List<Customer> listCustomer = repository.findAll();
System.out.println("FName: " + listCustomer.get(0).getFirstName());

Customer c = repository.findByFirstName("adff").get(0);
System.out.println("LName: " + c.getLastName());
return "Customer is created";
}

@GetMapping("/findall")
public List<Customer> findAll(){
List<Customer> customers = repository.findAll();
List<Customer> customerUI = new ArrayList<>();
for (Customer customer : customers) {
customerUI.add(new Customer(customer.getFirstName(),customer.getLastName()));
}
return customerUI;
}

@RequestMapping("/search/{id}")
public String search(@PathVariable long id){
String customer = "";
customer = repository.findById(id).toString();
return customer;
}

@RequestMapping("/searchbyfirstname/{firstname}")
public List<Customer> fetchDataByFirstName(@PathVariable String firstname){
List<Customer> customers = repository.findByFirstName(firstname);
List<Customer> customerUI = new ArrayList<>();
for (Customer customer : customers) {	
customerUI.add(new Customer(customer.getFirstName(),customer.getLastName()));
}
return customerUI;
}

@Transactional
@PostMapping("/multipleCalls")
public String multipleCalls(@RequestBody Customer customer, @RequestParam Integer pid) {
	insert(customer);
	update(customer);
	update2(customer);
	if(pid == 1)
	killSession();
	return  "Added";
}

@GetMapping("/getSession")
public List<SessionDetails> getSession() {
	return sessionRepository.getSession();
}

public Customer insert(Customer customer) {
	return repository.save(customer);
}

public Customer update(Customer customer) {
	customer.setLastName(customer.getLastName()+1);
	System.out.println("Last name will be updated to " + customer.getLastName());
	return repository.save(customer);
}

public Customer update2(Customer customer) {
	customer.setFirstName(customer.getFirstName()+2);
	System.out.println("First name will be updated to " + customer.getFirstName());
	return repository.save(customer);
}

public void killSession() {
	jdbcTemplate.execute("select pg_terminate_backend(pid) \r\n"
			+ "from pg_stat_activity\r\n"
			+ "where pid = pg_backend_pid();");
	System.out.println("Session killed");
}

}
