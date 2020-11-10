package br.com.vertigo.examples.jaegertutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Actuator guarantees "/actuator/health" endpoint
 */
@SpringBootApplication
public class JaegerTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaegerTutorialApplication.class, args);
	}

}
