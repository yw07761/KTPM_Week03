package com.example.customerservice.controller;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
