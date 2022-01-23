package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Service.CustomerDetailsService;
import com.portfolioAPI.portfolioapi.Service.PortfolioAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/customer")
public class portfolioAPIController {

    private final LoggingController logger;
    private final PortfolioAPIService portfolioAPIService;
    private final CustomerDetailsService customerDetailsService;

    @Autowired
    public portfolioAPIController(LoggingController logger, PortfolioAPIService portfolioAPIService, CustomerDetailsService customerDetailsService){
        this.logger = logger;
        this.portfolioAPIService = portfolioAPIService;
        this.customerDetailsService = customerDetailsService;
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        logger.logger.info(String.valueOf(portfolioAPIService.getCustomers()));
        return portfolioAPIService.getCustomers();
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public Optional<Customer> getCustomer(@RequestBody Customer customer){
//        logger.logger.info(customer.getName());
        return portfolioAPIService.getCustomer(customer);
    }

    @RequestMapping(value = "/addCustomer", method = { RequestMethod.GET, RequestMethod.POST })
    public void registerCustomer(
            @RequestBody Customer customer){
//        logger.logger.info(customer.getName());
        customerDetailsService.addCustomer(customer);
    }

    @RequestMapping(value = "/removeCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public void removeCustomer(
            @RequestBody Customer customer) {
        customerDetailsService.removeCustomer(customer);
    }


}
