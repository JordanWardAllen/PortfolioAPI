package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import com.portfolioAPI.portfolioapi.Service.CustomerDetailsService;
import com.portfolioAPI.portfolioapi.Service.PortfolioAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/customer")
@Slf4j
public class PortfolioAPIController {

    private final PortfolioAPIService portfolioAPIService;
    private final CustomerDetailsService customerDetailsService;

    @Autowired
    public PortfolioAPIController(PortfolioAPIService portfolioAPIService, CustomerDetailsService customerDetailsService){
        this.portfolioAPIService = portfolioAPIService;
        this.customerDetailsService = customerDetailsService;
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<CustomerEntity> getCustomers(){
        log.debug("Get customers Endpoint hit.");
        return portfolioAPIService.getCustomers();
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public CustomerEntity getCustomer(@RequestBody CustomerEntity customerEntity){
        log.debug("/getCustomer endpoint hit with request body {}", customerEntity.getId());
        return portfolioAPIService.getCustomer(customerEntity);
    }

    @RequestMapping(value = "/addCustomer", method = { RequestMethod.GET, RequestMethod.POST })
    public void registerCustomer(
            @RequestBody CustomerEntity customerEntity){
        customerDetailsService.postCustomer(customerEntity);
    }

    @RequestMapping(value = "/removeCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public void removeCustomer(
            @RequestBody CustomerEntity customerEntity) {
        customerDetailsService.removeCustomer(customerEntity);
    }


}
