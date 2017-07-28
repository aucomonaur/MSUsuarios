package com.aucomonaur.servicios;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.aucomonaur.modelo.Role;
import com.aucomonaur.modelo.Usuario;
import com.aucomonaur.repositorio.RoleRepository;
import com.aucomonaur.repositorio.RepositorioUsuarios;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario findUserByEmail(String email) {
		return repositorioUsuarios.findByEmail(email);
	}
	@Override
	public Usuario findOne(long id) {
		return repositorioUsuarios.findOne(id);
	}

	@Override
	public void saveUser(Usuario usuario) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setActive(1);
        Role userRole = roleRepository.findByRole("usuario");
        usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		repositorioUsuarios.save(usuario);
	}
	

}
