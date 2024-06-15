package lk.quontacom.task.ers;

import lk.quontacom.task.ers.controller.UserController;
import lk.quontacom.task.ers.model.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class EmployerRegisterSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployerRegisterSystemApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
