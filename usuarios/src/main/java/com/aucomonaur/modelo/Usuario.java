package com.aucomonaur.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue
	@Column(name="idusuario")
	private Long idUsuario;
	@Column(name="nombrecompleto")
	private String nombreCompleto;
	@Column(name="correo")
	private String correo;
	@Column(name="genero")
	private Integer genero;
	@Column(name="alias")
	private String alias;
	@Column(name="contrasenia")
	private String contrasenia;
	@Column(name="contraseniasalt")
	private String contraseniaSalt;
	@Column(name="idcarrera")
	private Long idCarrera;
	
	public Usuario() {}

	@JsonProperty(value="id_usuario")
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@JsonProperty(value="nombre")
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	@JsonProperty(value="correo")
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	@JsonIgnore
	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}
	
	@JsonProperty(value="alias")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@JsonIgnore
	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	@JsonIgnore
	public String getContraseniaSalt() {
		return contraseniaSalt;
	}

	public void setContraseniaSalt(String contraseniaSalt) {
		this.contraseniaSalt = contraseniaSalt;
	}

	@JsonIgnore
	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
	
	
	
}
