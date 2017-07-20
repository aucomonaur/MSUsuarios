package com.aucomonaur.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.aucomonaur.modelo.Usuario;
import com.aucomonaur.modelo.Json.UsuarioJson;
import com.aucomonaur.repositorio.RepositorioUsuarios;
import com.aucomonaur.servicios.UserService;


@RestController
@RequestMapping(value="/")
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;		

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registro", method = RequestMethod.GET)
	public ModelAndView registro(){
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
			bindingResult
					.rejectValue("email", "error.user",
							"Ya tenemos una cuenta registrada con ese correo");
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
	
	@RequestMapping(value="/usuario/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Usuario: " + usuario.getName() + " " + usuario.getAlias() + " (" + usuario.getEmail() + ")");
		modelAndView.addObject("adminMessage","Bienvenido");
		modelAndView.setViewName("usuario/home");
		return modelAndView;
	}
	@GetMapping(value="/get_usuario")
	public ResponseEntity<List<Usuario>> getUsuario(String id_usuario){
		List<Usuario> resultado = new ArrayList<>();
		
		//Si no esta bien estructurado, retornar bad request
		if(!id_usuario.matches("^([0-9]+)(,[0-9]+)*$"))
			return new ResponseEntity<List<Usuario>>(HttpStatus.BAD_REQUEST);
		
		String [] ids_usuarios = id_usuario.split(",");
		
		for (int i = 0; i < ids_usuarios.length; i++) {
			resultado.add(userService.findOne(Long.valueOf(ids_usuarios[i])));
		}
		
		return new ResponseEntity<List<Usuario>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value="/search_correo")
	public ResponseEntity<UsuarioJson> searchCorreo(String correo){
		
		Usuario usuario;
		usuario = userService.findUserByEmail(correo);
		
		return new ResponseEntity<UsuarioJson>(new UsuarioJson(usuario),HttpStatus.OK);
	}

}
