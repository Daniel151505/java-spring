package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Jhon");
		usuario.setApellido("Connor");
		usuario.setIdentificador("123.456.567-k");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesarFormulario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		// validador.validate(usuario, result);
		
		model.addAttribute("titulo", "Resultado form");

		if (result.hasErrors()) {
			//Map<String, String> errores = new HashMap<>();
			//result.getFieldErrors().forEach(err -> {
			//	errores.put(err.getField(),
			//			"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			//});

			//model.addAttribute("error", errores);
			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();
;		return "resultado";
	}

}
