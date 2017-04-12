package com.isa.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Value("${application.message}")
    private String message;

    @GetMapping
    @RequestMapping(value = "/")
    public String index(Model model) {
        Date time = new Date();
        model.addAttribute("message", message);
        model.addAttribute("time", time);
        return "index";
    }
}
