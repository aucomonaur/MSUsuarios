package com.aucomonaur.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aucomonaur.modelo.Usuario;

public interface RepositorioUsuarios extends JpaRepository<Usuario, Long>{
	
	//Consulta para buscar mediante correo	
	
	Usuario findByCorreo(String correo);

}
