package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    CustomerEntity findCustomerById(UUID id);

    CustomerEntity findCustomerByEmail(String email);

}
