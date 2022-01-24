package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Service.CustomerDetailsService;
import com.portfolioAPI.portfolioapi.Service.PortfolioAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/customer")
@Slf4j
public class PortfolioAPIController {

    private final LoggingController logger;
    private final PortfolioAPIService portfolioAPIService;
    private final CustomerDetailsService customerDetailsService;

    @Autowired
    public PortfolioAPIController(LoggingController logger, PortfolioAPIService portfolioAPIService, CustomerDetailsService customerDetailsService){
        this.logger = logger;
        this.portfolioAPIService = portfolioAPIService;
        this.customerDetailsService = customerDetailsService;
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<CustomerEntity> getCustomers(){
        log.debug("Get customers Endpoint hit.");
        return portfolioAPIService.getCustomers();
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public CustomerEntity getCustomer(@RequestBody UUID id){
        log.debug("/getCustomer endpoint hit with request body {}", id);
//        logger.logger.info(customer.getName());
        return portfolioAPIService.getCustomer(id);
    }

    @RequestMapping(value = "/addCustomer", method = { RequestMethod.GET, RequestMethod.POST })
    public void registerCustomer(
            @RequestBody Customer customer){
//        logger.logger.info(customer.getName());
        customerDetailsService.addCustomer(customer);
    }

    @RequestMapping(value = "/removeCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public void removeCustomer(
            @RequestBody CustomerEntity customerEntity) {
        customerDetailsService.removeCustomer(customerEntity);
    }


}
