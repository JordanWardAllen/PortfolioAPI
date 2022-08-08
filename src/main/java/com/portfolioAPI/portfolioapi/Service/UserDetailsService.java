package com.portfolioAPI.portfolioapi.Service;


import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import com.portfolioAPI.portfolioapi.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserDetailsService {

    private final UserRepository userRepository;
    private PortfolioAPIService portfolioAPIService;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void postUser(UserEntity userEntity) {
        addUser(userEntity);
    }

    // addUser method separated for future authentication control
    public void addUser(UserEntity userEntity) {
        try {
            userRepository.findUserByEmail(userEntity.getEmail());
        }
        catch (IllegalStateException e){
            log.error("Failed to create new User. Email {} already in use", userEntity.getEmail());
            e.printStackTrace();
        }
        saveUser(userEntity);

    }

    public UserEntity saveUser(UserEntity userEntity) {
        try {
            userRepository.save(userEntity);
        }
        catch (IllegalStateException e) {
            log.debug("User added" + userEntity);
            e.printStackTrace();
        }

        userRepository.save(userEntity);
        return userEntity;
    }

    public void removeUser(UserEntity userEntity) {
        try{
            userRepository.findUserByEmail(userEntity.getEmail());
        }
        catch(IllegalStateException e) {
            log.error("Email {} not in use by a User", userEntity.getEmail());
            e.printStackTrace();
        }

        userRepository.delete(userEntity);
    }

}
