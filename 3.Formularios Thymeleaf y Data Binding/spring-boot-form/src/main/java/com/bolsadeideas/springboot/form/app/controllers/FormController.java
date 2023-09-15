package com.bolsadeideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.NombreMayusculaEditors;
import com.bolsadeideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Pais;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.services.PaisService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PaisPropertyEditor paisEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento",new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "nombre" ,new NombreMayusculaEditors());
		binder.registerCustomEditor(String.class, "apellido" ,new NombreMayusculaEditors());
		binder.registerCustomEditor(Pais.class, "pais" , paisEditor);
		binder.registerCustomEditor(Role.class, "roles" , roleEditor);
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}
	
	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "México", "Chile", "Argentina", "Perú", "Colombia");
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("MX", "México");
		paises.put("CL", "Chile");
		return paises;
		
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
	
		return roles;
	}
	

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Jhon");
		usuario.setApellido("Connor");
		usuario.setIdentificador("123.456.567-k");
		usuario.setHabilitar(true);
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
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		
		return roles;
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		
		return this.roleService.listar();
	}
	
	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}
	

}
