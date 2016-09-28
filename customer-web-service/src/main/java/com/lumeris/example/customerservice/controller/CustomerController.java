package com.lumeris.example.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lumeris.example.customerservice.model.Customer;
import com.lumeris.example.customerservice.repo.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepo;

	@RequestMapping(value="customers/count", method = RequestMethod.GET)
	public int countCustomers(){
		return customerRepo.findAll().size();
	}

	@RequestMapping(value="customers", method = RequestMethod.GET)
	public List<Customer> allCustomers(){
		return customerRepo.findAll();
	}
	
	@RequestMapping(value="customers/{lastName}", method = RequestMethod.GET)
	public List<Customer> findCustomerByLastName(@PathVariable String lastName, @RequestParam(required=false, defaultValue="false") boolean ignoreCase) {
		return ignoreCase ? customerRepo.findByLastNameIgnoreCase(lastName) : customerRepo.findByLastName(lastName);
	}
}
