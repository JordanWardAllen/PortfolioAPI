package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.Model.Customer;
import com.portfolioAPI.portfolioapi.Service.portfolioAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/customer")
public class portfolioAPIController {

    private final LoggingController logger;
    private final portfolioAPIService customerService ;

    @Autowired
    public portfolioAPIController(LoggingController logger, portfolioAPIService customerService){
        this.logger = logger;
        this.customerService = customerService;
    }

    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        logger.logger.info(String.valueOf(customerService.getCustomers()));
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public Optional<Customer> getCustomer(@RequestBody Customer customer){
//        logger.logger.info(customer.getName());
        return customerService.getCustomer(customer);
    }

    @RequestMapping(value = "/addCustomer", method = { RequestMethod.GET, RequestMethod.POST })
    public void registerCustomer(
            @RequestBody Customer customer){
//        logger.logger.info(customer.getName());
        customerService.addCustomer(customer);
    }


}
