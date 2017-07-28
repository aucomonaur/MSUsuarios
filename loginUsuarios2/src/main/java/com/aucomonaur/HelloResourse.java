package com.aucomonaur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloResourse {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	@RequestMapping("/get_usuario")
	public String get_Usuario(String id_usuario){
		String url="http://eureka-server/get_usuario?id_usuario="+id_usuario;
		return restTemplate.getForObject(url, String.class);
		
	}
	@GetMapping
	@RequestMapping("/search_correo")
	public String search_correo(){
		String url="http://eureka-server/search_correo";
		return restTemplate.getForObject(url, String.class);
	}
}
