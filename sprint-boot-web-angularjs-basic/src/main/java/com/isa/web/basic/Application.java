package com.isa.web.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.isa")
public class Application {
	private final static Logger LOG = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		LOG.info("Application started");
		SpringApplication.run(Application.class, args);
	}
}
