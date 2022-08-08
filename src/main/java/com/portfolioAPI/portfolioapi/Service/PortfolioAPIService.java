package com.portfolioAPI.portfolioapi.Service;

import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import com.portfolioAPI.portfolioapi.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PortfolioAPIService {

    private final UserRepository userRepository;

    @Autowired
    public PortfolioAPIService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public UserEntity getUser(String email, String password){
        try {
            userRepository.findUserByEmailAndPassword(email, password);
        } catch (IllegalStateException e){
            log.error("Failed to register {}, {} with error {}", email, password, e);
        }
        return userRepository.findUserByEmailAndPassword(email, password);
    }

//    public CustomerEntity getCustomer(CustomerEntity customerEntity){
//        try {
//            customerRepository.findCustomerById(customerEntity.getId());
//        }
//        catch (IllegalStateException e){
//            log.error(NO_VALID_ID_ERROR);
//        }
//
//        return customerRepository.findCustomerById(customerEntity.getId());
//    }


}
