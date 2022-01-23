package com.portfolioAPI.portfolioapi.Service;


import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.portfolioAPI.portfolioapi.Constants.ServiceConstants.CREATE_NEW_CUSTOMER_EMAIL_ERROR;

@Service
public class CustomerDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        if(customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()){
            throw new IllegalStateException(CREATE_NEW_CUSTOMER_EMAIL_ERROR);
        }
        customerRepository.save(customer);

    }
    public void removeCustomer(Customer customer) {
        if(customerRepository.findCustomerByEmail(customer.getEmail()).isEmpty()) {
            throw new IllegalStateException("Customer does not exist");
        }
        customerRepository.delete(customer);
    }

}
