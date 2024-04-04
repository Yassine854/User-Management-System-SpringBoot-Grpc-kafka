package com.aniss.userservice;

import com.aniss.userservice.entities.User;
import com.aniss.userservice.repositories.IUserRepository;
import com.aniss.userservice.requests.CreateUser;
import com.aniss.userservice.services.IUserService;
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
	CommandLineRunner commandLineRunner(IUserService userService) {
		return args -> {
			// Create users
			CreateUser yassineUser = new CreateUser();
			yassineUser.setName("Yassine");

			CreateUser emnaUser = new CreateUser();
			emnaUser.setName("Emna");

			CreateUser rayenUser = new CreateUser();
			rayenUser.setName("Rayen");

			CreateUser farahUser = new CreateUser();
			farahUser.setName("Farah");

			// Save the users using IUserService
			User yassine = userService.create(yassineUser);
			User emna = userService.create(emnaUser);
			User rayen = userService.create(rayenUser);
			User farah = userService.create(farahUser);

			// Print the created users
			System.out.println("Users created: ");
			System.out.println(yassine);
			System.out.println(emna);
			System.out.println(rayen);
			System.out.println(farah);
		};
	}

}
