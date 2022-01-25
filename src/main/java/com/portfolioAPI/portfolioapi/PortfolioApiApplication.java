package com.portfolioAPI.portfolioapi;

import com.portfolioAPI.portfolioapi.Entity.CustomerEntity;
import com.portfolioAPI.portfolioapi.Repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class PortfolioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CustomerRepository customerRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				CustomerEntity customerEntity = new CustomerEntity(name, "Email", "password");
				customerRepository.save(customerEntity);
			});
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
