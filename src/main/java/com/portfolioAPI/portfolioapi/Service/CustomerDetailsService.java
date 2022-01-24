package com.portfolioAPI.portfolioapi.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerDetailsService {

    private final CustomerRepository customerRepository;
    private PortfolioAPIService portfolioAPIService;

    @Autowired
    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void postCustomer(CustomerEntity customerEntity) {
        addCustomer(customerEntity);
    }

    // AddCustomer method seperated for future authentication control
    public void addCustomer(CustomerEntity customerEntity) {
        try {
            customerRepository.findCustomerByEmail(customerEntity.getEmail());
        }
        catch (IllegalStateException e){
            log.error("Failed to create new customer. Email {} already in use", customerEntity.getEmail());
            e.printStackTrace();
        }
        saveCustomer(customerEntity);

    }

    public CustomerEntity saveCustomer(CustomerEntity customerEntity) {
        try {
            customerRepository.save(customerEntity);
        }
        catch (IllegalStateException e) {
            log.debug("Customer added" + customerEntity);
            e.printStackTrace();
        }

        customerRepository.save(customerEntity);
        return customerEntity;
    }

    public void removeCustomer(CustomerEntity customerEntity) {
        try{
            customerRepository.findCustomerByEmail(customerEntity.getEmail());
        }
        catch(IllegalStateException e) {
            log.error("Email {} not in use by a customer", customerEntity.getEmail());
            e.printStackTrace();
        }

        customerRepository.delete(customerEntity);
    }

}
