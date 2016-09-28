package com.lumeris.example.customerservice.controller;

import java.util.List;
import java.util.stream.Collectors;

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

	@RequestMapping(value="customers", method = RequestMethod.GET)
	public List<Customer> allCustomers(@RequestParam(required = false, defaultValue = "false") boolean sorted){
		List<Customer> customers = customerRepo.findAll();
		
		if ( sorted ) {
			customers = sortCustomers(customers);
		}
		
		return customers;
	}

	@RequestMapping(value = "customers/{lastName}", method = RequestMethod.GET)
	public List<Customer> findCustomerByLastName(@PathVariable String lastName,
			@RequestParam(required = false, defaultValue = "false") boolean sorted) {
		List<Customer> customersWithLastName = customerRepo.findByLastName(lastName);
		
		if ( sorted ) {
			customersWithLastName = sortCustomers(customersWithLastName);
		}
		
		return customersWithLastName;
	}
	
	private List<Customer> sortCustomers(List<Customer> customerList) {
		return customerList.stream().sorted((c1, c2) -> {
			int result = c1.getLastName().compareTo(c2.getLastName());
			if ( result == 0 ) {
				result = c1.getFirstName().compareTo(c2.getFirstName());
			}
			return result;
		}).collect(Collectors.toList());
	}
}
