package com.aucomonaur.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aucomonaur.modelo.Facultad;
import com.aucomonaur.modelo.Grupo;
import com.aucomonaur.modelo.Post;
import com.aucomonaur.modelo.Usuario;
import com.aucomonaur.modelo.Json.UsuarioJson;
import com.aucomonaur.repositorio.RepositorioUsuarios;
import com.aucomonaur.servicios.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import aj.org.objectweb.asm.TypeReference;

@RestController
@RequestMapping(value = "/")
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Facultad[] usrPost = mapper.readValue(new URL("file:///C:/Users/visita/Downloads/facultades.json"),
					Facultad[].class);
		} catch (IOException ex) {
			System.out.println("No se han encontrado datos de facultades");
			ex.printStackTrace();
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public ModelAndView registro() {
		ModelAndView modelAndView = new ModelAndView();
		Usuario usuario = new Usuario();
		modelAndView.addObject("user", usuario);
		modelAndView.setViewName("registro");
		return modelAndView;
	}

	@RequestMapping(value = "/registro", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Usuario usuario, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Usuario userExists = userService.findUserByEmail(usuario.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "Ya tenemos una cuenta registrada con ese correo");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registro");
		} else {
			userService.saveUser(usuario);
			modelAndView.addObject("successMessage", "Ud se ha registrado con exito");
			modelAndView.addObject("user", new Usuario());
			modelAndView.setViewName("registro");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/usuario/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = userService.findUserByEmail(auth.getName());
		ObjectMapper mapper = new ObjectMapper();
		boolean hayDatos = false;
		try {
			// d='[{"id_grupo":1,"nombre":"Grupo1","materia":"Tecnologias
			// Web","carrera":"Sistemas","facultad":"Ingenieria","periodo_academico":"26
			// Jul 2017 05:00:00 GMT"}]';
			Grupo[] usrPost = mapper.readValue(new URL("file:///C:/Users/visita/Downloads/ex.json"), Grupo[].class);
			// Grupo[] usrPost = mapper.readValue(new
			// URL("http://172.16.147.43:9092/get_grupos_usuario?id_usuario="+usuario.getId()),
			// Grupo[].class);
			// List<Grupo> usrPost = mapper.readValues(new
			// URL("localhost:9092/get_grupos_usuario?id_usuario="+usuario.getId()),
			// new TypeReference(){});
			for (Grupo g : usrPost) {
				g.setIdUsuario(usuario.getId());
			}
			modelAndView.addObject("grupos", usrPost);
			System.out.println("asdf" + usrPost);
			hayDatos = true;
		} catch (IOException ex) {
			System.out.println("No se ha encontrado datos de grupos");
			ex.printStackTrace();
		}
		modelAndView.addObject("hayDatosGrupos", hayDatos);
		modelAndView.addObject("userName",
				"Usuario: " + usuario.getName() + " " + usuario.getAlias() + " (" + usuario.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Bienvenido");
		// Redirige a la creación de grupos necesaria la Ip de Creación de
		// grupos
		modelAndView.addObject("crearGrupo", "<a href=http://172.16.147.151:9092?id_usuario=" + usuario.getId()
				+ "><button>Crear Grupo</button></a> ");
		modelAndView.setViewName("usuario/home");
		return modelAndView;
	}

	@GetMapping(value = "/get_usuario")
	public ResponseEntity<List<Usuario>> getUsuario(String id_usuario) {
		List<Usuario> resultado = new ArrayList<>();

		// Si no esta bien estructurado, retornar bad request
		if (!id_usuario.matches("^([0-9]+)(,[0-9]+)*$"))
			return new ResponseEntity<List<Usuario>>(HttpStatus.BAD_REQUEST);

		String[] ids_usuarios = id_usuario.split(",");

		for (int i = 0; i < ids_usuarios.length; i++) {
			resultado.add(userService.findOne(Long.valueOf(ids_usuarios[i])));
		}

		return new ResponseEntity<List<Usuario>>(resultado, HttpStatus.OK);
	}

	@GetMapping(value = "/search_correo")
	public ResponseEntity<UsuarioJson> searchCorreo(String correo) {

		Usuario usuario;
		usuario = userService.findUserByEmail(correo);

		return new ResponseEntity<UsuarioJson>(new UsuarioJson(usuario), HttpStatus.OK);
	}

}
