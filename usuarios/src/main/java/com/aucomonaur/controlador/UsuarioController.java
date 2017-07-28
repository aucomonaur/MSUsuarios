package com.aucomonaur.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aucomonaur.modelo.Usuario;
import com.aucomonaur.modelo.Json.UsuarioJson;
import com.aucomonaur.repositorio.RepositorioUsuarios;

@RestController
@RequestMapping(value="/")
public class UsuarioController {

	@Autowired
	RepositorioUsuarios repositorioUsuarios;
	
	
	@GetMapping(value="/get_usuario")
	public ResponseEntity<List<Usuario>> getUsuario(String id_usuario){
		List<Usuario> resultado = new ArrayList<>();
		
		//Si no esta bien estructurado, retornar bad request
		if(!id_usuario.matches("^([0-9]+)(,[0-9]+)*$"))
			return new ResponseEntity<List<Usuario>>(HttpStatus.BAD_REQUEST);
		
		String [] ids_usuarios = id_usuario.split(",");
		
		for (int i = 0; i < ids_usuarios.length; i++) {
			resultado.add(repositorioUsuarios.findOne(Long.valueOf(ids_usuarios[i])));
		}
		
		return new ResponseEntity<List<Usuario>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value="/search_correo")
	public ResponseEntity<UsuarioJson> searchCorreo(String correo){
		
		Usuario usuario;
		usuario = repositorioUsuarios.findByCorreo(correo);
		
		return new ResponseEntity<UsuarioJson>(new UsuarioJson(usuario),HttpStatus.OK);
	}
}
