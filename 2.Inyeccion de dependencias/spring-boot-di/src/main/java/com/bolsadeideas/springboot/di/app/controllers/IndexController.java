package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class IndexController {

	@GetMapping({"/", "", "/index"}) 
	public String index(Model model){
		return "index";
	}
	
}
