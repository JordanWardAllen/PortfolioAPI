package com.portfolioAPI.portfolioapi.Service;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.portfolioAPI.portfolioapi.Constants.ServiceConstants.NO_VALID_ID_ERROR;

@Service
@Slf4j
public class PortfolioAPIService {

    private final CustomerRepository customerRepository;

    @Autowired
    public PortfolioAPIService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<CustomerEntity> getCustomers(){
        return customerRepository.findAll();
    }

    public CustomerEntity getCustomer(CustomerEntity customerEntity){
        try {
            customerRepository.findCustomerById(customerEntity.getId());
        }
        catch (IllegalStateException e){
            log.error(NO_VALID_ID_ERROR);
        }

        return customerRepository.findCustomerById(customerEntity.getId());
    }


}
