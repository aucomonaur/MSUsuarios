package com.aucomonaur.servicios;

import com.aucomonaur.modelo.Usuario;

public interface UserService {
	public Usuario findUserByEmail(String email);
	public void saveUser(Usuario usuario);
	public Usuario findOne(long id);
}
