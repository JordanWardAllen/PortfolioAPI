package com.portfolioAPI.portfolioapi.Controller;

import com.portfolioAPI.portfolioapi.Model.CustomerModel;
import com.portfolioAPI.portfolioapi.Service.portfolioAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;
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

//    @RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
    @GetMapping(path = "/getCustomers")
    public List<CustomerModel> getCustomers(){
        logger.logger.info(String.valueOf(customerService.getCustomers()));
        return customerService.getCustomers();
    }

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public CustomerModel getCustomer(@RequestBody CustomerModel customer){
        logger.logger.info(customer.getName());
        return customerService.getCustomer(customer.getId());
    }

    @RequestMapping(value = "/addCustomer", method = { RequestMethod.GET, RequestMethod.POST })
    public void registerCustomer(
            @RequestBody CustomerModel customer){
        logger.logger.info(customer.getName());
        customerService.addCustomer(customer);
    }


}