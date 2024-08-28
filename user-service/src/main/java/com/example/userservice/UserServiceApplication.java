package com.example.userservice;

import com.example.userservice.models.UserEntity;
import com.example.userservice.repositories.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserEntityRepository userEntityRepository){
		return args -> {
			/*UserEntity user = new UserEntity("cuak@aol.com", "Qu Ack", "12345");
			userEntityRepository.save(user).subscribe();
			UserEntity user2 = new UserEntity("john@gmail.com", "John Doe", "12345");
			userEntityRepository.save(user2).subscribe();*/
		};
	}
}
