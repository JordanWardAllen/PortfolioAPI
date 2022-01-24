package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findCustomerById(Long id);

    CustomerEntity findCustomerByEmail(String email);

    CustomerEntity findAllById(Long id);
}

