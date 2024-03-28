package com.example.customerservice.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.example.customerservice.model.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Sử dụng annotation @Retry để đánh dấu phương thức gọi CustomerService
    @Retry(name = "customerServiceRetry")
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    @Cacheable(value = "customers")
    public Customer getCustomer(Long customerId) {
        // Lấy dữ liệu từ cơ sở dữ liệu hoặc bất kỳ nguồn dữ liệu nào khác
        // Trong ví dụ này, chỉ là một phần giả định
        return retrieveCustomerFromDatabase(customerId);
    }

    // Phương thức giả định lấy dữ liệu từ cơ sở dữ liệu
    private Customer retrieveCustomerFromDatabase(Long customerId) {
        // Gọi repository hoặc truy vấn cơ sở dữ liệu
        // Đây chỉ là một ví dụ giả định
        return new Customer(customerId, "John Doe", "123 Main St");
    }
}

