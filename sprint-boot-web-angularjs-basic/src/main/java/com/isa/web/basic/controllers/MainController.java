package com.isa.web.basic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

	@GetMapping
	@RequestMapping(path = "/")
	public String index() {
		LOG.info("Entered into index()");
		return "index";
	}
}
