package com.orderservice.shoppit.service;

import com.orderservice.shoppit.entity.Customer;
import com.orderservice.shoppit.repository.CustomerRepository;
import com.orderservice.shoppit.request.CustomerRequest;
import com.orderservice.shoppit.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRequest.getCustomer();
        Customer addedCustomer = customerRepository.save(customer);
        return new CustomerResponse("success", "customer added successfully", addedCustomer);
    }

    public CustomersResponse allCustomers() {
        return new CustomersResponse("success", "All products", customerRepository.findAll());
    }

    public CustomerResponse getaCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        return new CustomerResponse("success", "Customer found", customer);
    }

    public CustomerResponse updateCustomer(CustomerRequest customer) {
        Customer customerExists = customerRepository.findById(customer.getCustomer().getId()).get();
        customerExists.setName(customer.getCustomer().getName());
        customerExists.setAge(customer.getCustomer().getAge());
        customerExists.setCity(customer.getCustomer().getCity());
        customerExists.setEmail(customer.getCustomer().getEmail());

        customerRepository.save(customer.getCustomer());
        return new CustomerResponse("success", "Customer updated", customer.getCustomer());
    }

    public void deleteCustomer(String id) {
        Boolean customerExists = customerRepository.existsById(id);
        customerRepository.deleteById(id);
    }
}
