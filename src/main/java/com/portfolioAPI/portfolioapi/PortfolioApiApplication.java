package com.portfolioAPI.portfolioapi;

import com.portfolioAPI.portfolioapi.Entity.UserEntity;
import com.portfolioAPI.portfolioapi.Repository.UserRepository;
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
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			Stream.of("John").forEach(name -> {
				UserEntity userEntity = new UserEntity(name, "Email", "password");
				userRepository.save(userEntity);
			});
			userRepository.findAll().forEach(System.out::println);
		};
	}

}
