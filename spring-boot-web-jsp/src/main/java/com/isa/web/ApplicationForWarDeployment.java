package com.isa.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.isa.web.controller")
public class ApplicationForWarDeployment extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationForWarDeployment.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationForWarDeployment.class, args);
	}
}
