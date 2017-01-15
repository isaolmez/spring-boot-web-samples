package com.isa.web.controller;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CounterController {

	private AtomicInteger counter;
	
	public CounterController() {
		counter = new AtomicInteger(0);
	}

	@GetMapping
	@RequestMapping(value = "/count")
	public String count(Map<String, Object> modelMap) {
		modelMap.put("counter", counter.incrementAndGet());
		return "count";
	}
}
