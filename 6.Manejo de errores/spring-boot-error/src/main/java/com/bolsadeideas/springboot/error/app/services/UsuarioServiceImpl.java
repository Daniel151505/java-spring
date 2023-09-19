package com.bolsadeideas.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.app.models.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;
	
	
	
	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1, "Andres", "Gales"));
		this.lista.add(new Usuario(2, "Pepa", "Gales"));
		this.lista.add(new Usuario(3, "Lolo", "Gales"));
		this.lista.add(new Usuario(4, "Sara", "Gales"));
		this.lista.add(new Usuario(5, "Mafer", "Gales"));
		this.lista.add(new Usuario(6, "Paco", "Gales"));
		this.lista.add(new Usuario(7, "Diego", "Gales"));
	}

	@Override
	public List<Usuario> listar() {
		return lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		
		for(Usuario u: this.lista) {
			if(u.getId().equals(id)) {
				resultado = u;
				break;
			}
		}
		
		return resultado;
	}

}
