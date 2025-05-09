package com.portfolioAPI.portfolioapi.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolioAPI.portfolioapi.DTO.UserDTO;
import com.portfolioAPI.portfolioapi.Mapper.UserMapper;
import com.portfolioAPI.portfolioapi.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;


@Service
@Slf4j
@EnableConfigurationProperties
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Iterable<UserDTO> getUsers() {
       return userMapper.mapUserDTOToUserEntityIterator(userRepository.findAll());
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findUserById(id).map(userMapper::mapUserEntityToUserDTO)
                .orElseThrow(()-> new NoSuchElementException("Unable to find User with ID: " + id));
    }

    public UserDTO getUserEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password).map(userMapper::mapUserEntityToUserDTO)
                .orElseThrow(() -> new NoSuchElementException("Unable to find User with Credentials: " + email + " " + password));
    }

    public void createUser(UserDTO user) {
        try {
            userRepository.save(userMapper.mapUserDTOToUserEntity((user)));
        } catch (Exception e) {
            throw new NoSuchElementException(user.getName() + " already exists. Please provide a unique user name.");
        }
    }

    public void updateUser(UserDTO user, String name) {
        if(!user.getName().equals(name)) {
            throw new IllegalArgumentException("Request body name must match request parameter name");
        }
        userRepository.findUserByName(name)
                .map(existing -> {
                    try {
                        String dtoJson = objectMapper.writeValueAsString(user);
                        objectMapper.readerForUpdating(existing)
                                .readValue(dtoJson);
                    } catch (IOException e) {
                        throw new RuntimeException("failed to map");
                    }
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new NoSuchElementException(user.getName() + " does not exist."));
    }

    public void removeUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No such User with ID: " + id);
        }
    }

}
