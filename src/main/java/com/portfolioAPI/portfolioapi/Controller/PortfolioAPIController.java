package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.DTO.UserDTO;
import com.portfolioAPI.portfolioapi.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/user")
@Slf4j
@CrossOrigin(originPatterns = "http://localhost:4200")
public class PortfolioAPIController {

    private final UserService userService;

    @Autowired
    public PortfolioAPIController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public Iterable<UserDTO> getUsers() {
        log.debug("Get Users" +
                " Endpoint hit.");
        return userService.getUsers();
    }

    @RequestMapping(value = "/getUserById", method = {RequestMethod.GET})
    public UserDTO getUserById(@RequestParam Long id) {
        log.debug("/getUserById endpoint hit with request body {}", id);
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/getUserByEmailAndPassword", method = {RequestMethod.GET})
    public UserDTO getUserByEmailAndPassword(@RequestParam String email, String password) {
        log.debug("/getUserByEmailAndPassword received request params: " + email + " " + password);
        return userService.getUserEmailAndPassword(email, password);
    }

    @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    public ResponseEntity<String> createUser(@Valid
                                             @RequestBody UserDTO user) {
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }

    @RequestMapping(value = "/updateUser", method = {RequestMethod.PUT})
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDTO user, @RequestParam String name) {
        userService.updateUser(user, name);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully updated User " + name);
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.DELETE})
    public ResponseEntity<String> removeUser(@Valid
                           @RequestParam Long id) {
        userService.removeUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted User with ID " + id);
    }

}
