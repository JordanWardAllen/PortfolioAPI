package com.portfolioAPI.portfolioapi.Service;

import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static com.portfolioAPI.portfolioapi.Constants.ServiceConstants.NO_VALID_ID_ERROR;
import static com.portfolioAPI.portfolioapi.Constants.ServiceConstants.CREATE_NEW_CUSTOMER_EMAIL_ERROR;

@Service
public class portfolioAPIService {

    private final CustomerRepository customerRepository;

    @Autowired
    public portfolioAPIService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(Customer customer){
        Optional<Customer> CustomerOptional = customerRepository.findCustomerById(customer.getId());
        if(CustomerOptional.isEmpty()){
            throw new IllegalStateException(NO_VALID_ID_ERROR);
        }
        return customerRepository.findCustomerById(customer.getId());
    }

    public void addCustomer(Customer customer) {
        if(customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()){
            throw new IllegalStateException(CREATE_NEW_CUSTOMER_EMAIL_ERROR);
        }
        customerRepository.save(customer);

    }
}
