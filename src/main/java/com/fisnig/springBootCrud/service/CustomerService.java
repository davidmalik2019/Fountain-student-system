package com.fisnig.springBootCrud.service;

import com.fisnig.springBootCrud.model.Customer;
import com.fisnig.springBootCrud.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> listAll(String keyword) {
        if (keyword != null) {
            return customerRepository.search(keyword);
        }
        return (List<Customer>) customerRepository.findAll();
    }
}
