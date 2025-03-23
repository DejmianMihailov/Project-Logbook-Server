package com.flight.logbook_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LogbookServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(LogbookServerApplication.class, args);
	}

}
