package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
//public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
//
//    CustomerEntity findCustomerById(Long id);
//
//    CustomerEntity findCustomerByEmail(String email);
//
//}


@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findCustomerById(Long id);

    CustomerEntity findCustomerByEmail(String email);

}

