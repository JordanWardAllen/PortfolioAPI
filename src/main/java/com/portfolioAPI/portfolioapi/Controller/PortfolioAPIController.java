package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import com.portfolioAPI.portfolioapi.Service.UserDetailsService;
import com.portfolioAPI.portfolioapi.Service.PortfolioAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(path="/api/v1/customer")
@Slf4j
@CrossOrigin(originPatterns = "http://localhost:4200")
public class PortfolioAPIController {

    private final PortfolioAPIService portfolioAPIService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public PortfolioAPIController(PortfolioAPIService portfolioAPIService, UserDetailsService userDetailsService){
        this.portfolioAPIService = portfolioAPIService;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public Iterable<UserEntity> getUsers(){
        log.debug("Get Users" +
                " Endpoint hit.");
        return portfolioAPIService.getUsers();
    }

    @RequestMapping(value = "/getUser", method = {RequestMethod.GET, RequestMethod.POST})
    public UserEntity getUser(@RequestBody UserEntity userEntity){
        log.debug("/getUser endpoint hit with request body {}", userEntity.getId());
        return portfolioAPIService.getUser(userEntity.getEmail(), userEntity.getPassword());
    }

    @RequestMapping(value = "/addUser", method = { RequestMethod.GET, RequestMethod.POST })
    public void registerUser(
            @RequestBody UserEntity userEntity){
        userDetailsService.postUser(userEntity);
    }

    @RequestMapping(value = "/removeUser", method = {RequestMethod.GET, RequestMethod.POST})
    public void removeUser(
            @RequestBody UserEntity userEntity) {
        userDetailsService.removeUser(userEntity);
    }


}
