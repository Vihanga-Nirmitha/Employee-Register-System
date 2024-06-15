package lk.quontacom.task.ers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configuration
public class EmployerRegisterSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployerRegisterSystemApplication.class, args);
	}
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// Providing a default implementation for AuthenticationManager bean
		return authentication -> {
			throw new UnsupportedOperationException("authenticationManagerBean not supported");
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
