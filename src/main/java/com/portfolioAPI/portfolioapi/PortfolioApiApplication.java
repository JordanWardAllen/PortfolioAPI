package com.portfolioAPI.portfolioapi;

import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import com.portfolioAPI.portfolioapi.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@ConfigurationPropertiesScan
public class PortfolioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			UserEntity userEntity = new UserEntity("name", "email", "password");
			userRepository.save(userEntity);
//			userRepository.findAll().forEach(System.out::println);
		};
	}

}
