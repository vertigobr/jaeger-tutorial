package br.com.vertigo.examples.jaegertutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/*
 * Actuator guarantees "/actuator/health" endpoint
 */
@SpringBootApplication
@RestController
public class JaegerTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaegerTutorialApplication.class, args);
	}

}
