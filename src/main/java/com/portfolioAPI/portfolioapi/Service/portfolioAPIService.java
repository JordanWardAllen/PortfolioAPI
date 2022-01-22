package com.portfolioAPI.portfolioapi.Service;

import com.portfolioAPI.portfolioapi.Model.CustomerModel;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class portfolioAPIService {

    private final CustomerRepository customerRepository;

    @Autowired
    public portfolioAPIService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

//    @GetMapping
    public List<CustomerModel> getCustomers(){
        return customerRepository.findAll();
    }

    public CustomerModel getCustomer(Long id){
        System.out.println(id);
        return customerRepository.getById(id);
    }

    public void addCustomer(CustomerModel customer) {
        System.out.println(customer);
    }
}
