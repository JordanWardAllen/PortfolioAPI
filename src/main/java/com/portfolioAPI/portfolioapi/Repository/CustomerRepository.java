package com.portfolioAPI.portfolioapi.Repository;

import com.portfolioAPI.portfolioapi.Model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
}
