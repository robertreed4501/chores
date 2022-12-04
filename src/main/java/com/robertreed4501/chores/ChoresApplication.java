package com.robertreed4501.chores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@CrossOrigin
public class ChoresApplication {

	public static void main(String[] args) {

		SpringApplication springApplication = new SpringApplication(ChoresApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());     // register PID write to spring boot. It will write PID to file
		springApplication.run(args);
		/*SpringApplication.run(ChoresApplication.class, args);*/
	}


}
