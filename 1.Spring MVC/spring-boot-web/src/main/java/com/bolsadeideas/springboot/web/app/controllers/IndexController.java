package com.bolsadeideas.springboot.web.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${texto.indexController.index.titulo}")
	private String textoIndex;
	
	@Value("${texto.indexController.perfil.titulo}")
	private String textoPerfil;
	
	@Value("${texto.indexController.listar.titulo}")
	private String textoListar;

	@GetMapping({ "/index", "/", "", "/home" })
	public String index(Model model) {
		model.addAttribute("titulo", textoIndex);
		return "index";
	}

	@GetMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Andrés");
		usuario.setApellido("Solis");
		usuario.setEmail("andres@gmail.com");

		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil + ": ".concat(usuario.getNombre()));

		return "perfil";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		// List<Usuario> usuarios = new ArrayList<>();
		// usuarios.add(new Usuario("Andrés", "Guzmán", "andres@correo.com"));
		// usuarios.add(new Usuario("Sara", "Guzmán", "sara@correo.com"));
		// usuarios.add(new Usuario("Mayra", "Guzmán", "mayra@correo.com"));
		model.addAttribute("titulo", textoListar);
		// model.addAttribute("usuarios", usuarios);

		return "listar";
	}

	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios() {
		List<Usuario> usuarios = Arrays.asList(new Usuario("Andrés", "Guzmán", "andres@correo.com"),
				new Usuario("Sara", "Guzmán", "sara@correo.com"), new Usuario("Mayra", "Guzmán", "mayra@correo.com"),
				new Usuario("Mario", "Carmona", "mario@correo.com"));

		return usuarios;
	}

}
