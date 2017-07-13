package com.aucomonaur.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idusuario")
	private Long id;
	@Column(name = "nombrecompleto")
	@NotEmpty(message = "*Por favor ingrese su nombre")
	private String name;
	@Column(name = "correo")
	@Email(message = "*Por favor ingrese un correo valido")
	@NotEmpty(message = "*Por favor ingrese un correo")	
	private String email;
	@Column(name = "alias")
	@NotEmpty(message = "*Por favor ingrese su alias")
	private String alias;
	@Column(name="genero")
	@NotNull(message = "*Por favor ingrese su genero")
	private int genero;	
	@Column(name = "password")
	@Length(min = 5, message = "*Su contraseña debe tener como minimo 5 caracteres")
	@NotEmpty(message = "*Por favor ingrese una contraseña")
	@Transient	
	private String password;		
	@Column(name = "active")
	private int active;
	@Column(name="contraseniasalt")
	private String contraseniaSalt;
	@NotNull(message = "*Por favor ingrese su genero")
	@Column(name="idcarrera")
	private int idCarrera;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@JsonProperty(value="id_usuario")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@JsonProperty(value="nombre")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty(value="alias")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	@JsonProperty(value="correo")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@JsonIgnore
	public int getActive() {
		return active;
	}
	
	public void setActive(int active) {
		this.active = active;
	}
	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@JsonIgnore
	public String getContraseniaSalt() {
		return contraseniaSalt;
	}
	
	public void setContraseniaSalt(String contraseniaSalt) {
		this.contraseniaSalt = contraseniaSalt;
	}

	@JsonIgnore
	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}
	@JsonIgnore
	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
	
}
