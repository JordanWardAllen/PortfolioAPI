package com.portfolioAPI.portfolioapi.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolioAPI.portfolioapi.DTO.UserDTO;
import com.portfolioAPI.portfolioapi.Repository.RedisRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
@EnableConfigurationProperties
public class UserService {

    private final RedisRepositoryImpl redis;

    ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public UserService(RedisRepositoryImpl redis) {
        this.redis = redis;
    }

    public List<UserDTO> getUsers() {
        return redis.findAll();
    }

    public UserDTO getUserByName(String name) {
        return redis.findUserByName(name)
                .orElseThrow(()-> new NoSuchElementException("Unable to find User with Name: " + name));
    }

    public void createUser(UserDTO user) {
        try {
            redis.createUser(user);
        } catch (Exception e) {
            throw new NoSuchElementException(user.getName() + " already exists. Please provide a unique user name.");
        }
    }

    public void updateUser(UserDTO user, String name) {
        if(!user.getName().equals(name)) {
            throw new IllegalArgumentException("Request body name must match request parameter name");
        }
        redis.findUserByName(name)
                .map(existing -> {
                    try {
                        String dtoJson = objectMapper.writeValueAsString(user);
                        objectMapper.readerForUpdating(existing)
                                .readValue(dtoJson);
                    } catch (IOException e) {
                        throw new RuntimeException("failed to map");
                    }
                    redis.saveUser(existing);
                    return true;
                }).orElseThrow(() -> new NoSuchElementException(user.getName() + " does not exist."));
    }

    public void removeUser(String name) {
        redis.findUserByName(name)
                .map(item -> {
                    redis.deleteUser(name);
                    return true;
                }).orElseThrow(() -> new NoSuchElementException(name + " does not exist."));
    }

}
