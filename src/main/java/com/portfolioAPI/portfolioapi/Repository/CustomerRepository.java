package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @Query("SELECT c FROM Customer Where c.id = ?0")
    Optional<Customer> findCustomerById(Long id);
}
