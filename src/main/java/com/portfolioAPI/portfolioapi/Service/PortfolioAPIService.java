package com.portfolioAPI.portfolioapi.Service;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

import static com.portfolioAPI.portfolioapi.Constants.ServiceConstants.NO_VALID_ID_ERROR;

@Service
public class PortfolioAPIService {

    private final CustomerRepository customerRepository;

    @Autowired
    public PortfolioAPIService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> getCustomers(){
        return customerRepository.findAll();
    }

    public CustomerEntity getCustomer(UUID id){
        CustomerEntity customerEntity = customerRepository.findCustomerById(id);
        if(customerEntity == null){
            throw new IllegalStateException(NO_VALID_ID_ERROR);
        }
        return customerRepository.findCustomerById(id);
    }


}
