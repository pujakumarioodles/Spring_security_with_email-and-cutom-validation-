package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	
	@GetMapping("/hello")
	public String sayHello(Model model, @RequestParam(defaultValue = "puja is the great" ,
	required = false)String name) {;
		model.addAttribute("name", name);
		return "hello";
	}
	
	
	@GetMapping("/home")
	public String home() {
		return "hello";
	}

}
