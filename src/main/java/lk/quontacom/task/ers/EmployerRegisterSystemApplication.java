package lk.quontacom.task.ers;

import lk.quontacom.task.ers.service.RoleService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication

public class EmployerRegisterSystemApplication {
	private final RoleService roleService;

	public EmployerRegisterSystemApplication(RoleService roleService) {
		this.roleService = roleService;
		roleService.feedUserRole();
	}

	public static void main(String[] args) throws JRException {
		SpringApplication.run(EmployerRegisterSystemApplication.class, args);

	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
