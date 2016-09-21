package com.lumeris.example.customerservice.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.lumeris.example.customerservice.model.Customer;

@Component
public class CustomerRepository {

	/**
	 * Fake stub for dummy data.
	 * 
	 * @return All Customers known in repository.
	 */
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(createCustomer("Baloo", "Bear"));
		customers.add(createCustomer("Kit", "Cloudkicker"));
		customers.add(createCustomer("Rebecca", "Cunningham"));
		customers.add(createCustomer("Molly", "Cunningham"));
		customers.add(createCustomer("Shere", "Khan"));
		return customers;
	}
	
	public List<Customer> findByLastName(String lastName) {
		return findAll().stream().filter(c -> c.getLastName().equals(lastName)).collect(Collectors.toList());
	}

	private Customer createCustomer(String firstName, String lastName) {
		Customer c = new Customer();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		return c;
	}

}
