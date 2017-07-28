package com.aucomonaur.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Grupo {
	private long id_grupo;
	private String nombre;
	private String correo;
	private String materia;
	private String carrera;
	private String facultad;
	private String periodo_academico;
	private String idUsuario;

	@JsonIgnore
	public String getIdUsuario() {
		return "http://172.16.147.8:9092/chat?id_usuario=" + idUsuario + "&id_grupo="+String.valueOf(id_grupo);
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = String.valueOf(idUsuario);
	}

	public String getPeriodo_academico() {
		return periodo_academico;
	}

	public void setPeriodo_academico(String periodo_academico) {
		this.periodo_academico = periodo_academico;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public long getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(long id_grupo) {
		this.id_grupo = id_grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	// public String getURLChat() {
	// // return "http://172.16.147.8:9092/?id_usuario=" +
	// // String.valueOf(this.idUsuario) + "&id_grupo="
	// // + String.valueOf(this.id_grupo);
	// return "\"asdf\"";
	//
	// }

}
