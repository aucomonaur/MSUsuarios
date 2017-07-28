package com.aucomonaur.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aucomonaur.modelo.Usuario;

@Repository("userRepository")
public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
	 Usuario findByEmail(String email);
}
