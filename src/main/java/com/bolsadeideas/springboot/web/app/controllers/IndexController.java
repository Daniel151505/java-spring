package com.bolsadeideas.springboot.web.app.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping({"/index", "/", "/home"})
	public String index(Map<String, Object> map) {
		map.put("titulo", "Hola Spring Framework");
		return "index";
	}
}
