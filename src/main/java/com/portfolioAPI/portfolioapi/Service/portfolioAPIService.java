package com.portfolioAPI.portfolioapi.Service;

import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class portfolioAPIService {

    private final CustomerRepository customerRepository;

    @Autowired
    public portfolioAPIService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(Customer customer){
        Optional<Customer> CustomerOptional = customerRepository.findCustomerById(customer.getId());
        if(CustomerOptional.isEmpty()){
            throw new IllegalStateException("Couldn't find ID");
        }
        return customerRepository.findCustomerById(customer.getId());
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);

    }
}
