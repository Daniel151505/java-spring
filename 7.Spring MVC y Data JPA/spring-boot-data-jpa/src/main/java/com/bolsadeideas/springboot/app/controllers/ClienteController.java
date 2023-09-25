package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {
	
	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;
	
	@GetMapping("/listar")
	public String Listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
	@GetMapping(value="/form")
	public String crear(Map <String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@PostMapping(value="/form")
	public String guardar(Cliente cliente) {
		clienteDao.save(cliente);
		return "redirect:listar";
	}
	
}
