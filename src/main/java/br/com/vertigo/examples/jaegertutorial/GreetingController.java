package br.com.vertigo.examples.jaegertutorial;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@RestController
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	private RestTemplate restTemplate;
	private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	public GreetingController(RestTemplateBuilder builder) {
    	this.restTemplate = builder.build();
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
	}

	@GetMapping("/hail")
	public Greeting hail() {
		Greeting greet = restTemplate.getForObject("http://localhost:9000/greeting?name=Hydra", Greeting.class);
		log.info(greet.toString());
		return greet;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}