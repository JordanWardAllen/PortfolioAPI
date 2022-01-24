package com.portfolioAPI.portfolioapi.Service;


import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.portfolioAPI.portfolioapi.Constants.ServiceConstants.CREATE_NEW_CUSTOMER_EMAIL_ERROR;

@Service
@Slf4j
public class CustomerDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // AddCustomer method seperated for future authentication control
    public void addCustomer(Customer customer) {
        if(customerRepository.findCustomerByEmail(customer.getEmail()).isPresent()){
            log.error("Failed to create new customer. Email {} already in use", customer.getEmail());
            throw new IllegalStateException(CREATE_NEW_CUSTOMER_EMAIL_ERROR);
        }

        saveCustomer(customer);

    }

    public void saveCustomer(Customer customer) {
        log.debug("Customer added" + customer);
        customerRepository.save(customer);
    }

    public void removeCustomer(Customer customer) {
        if(customerRepository.findCustomerByEmail(customer.getEmail()).isEmpty()) {
            log.error("Email {} not in use by a customer", customer.getEmail());
            throw new IllegalStateException("Customer does not exist");
        }
        customerRepository.delete(customer);
    }

}
