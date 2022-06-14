package wwhstudycleanarchproject.no24shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class No24shopApplication {

	public static void main(String[] args) {
		SpringApplication.run(No24shopApplication.class, args);
	}

}
