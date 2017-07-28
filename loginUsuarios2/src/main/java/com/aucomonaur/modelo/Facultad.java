package com.aucomonaur.modelo;

public class Facultad {
	private long id_facultad;
	private String nombre;
	private Carrera[] carreras;

	public long getId_facultad() {
		return id_facultad;
	}

	public void setId_facultad(long id_facultad) {
		this.id_facultad = id_facultad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Carrera[] getCarreras() {
		return carreras;
	}

	public void setCarreras(Carrera[] carreras) {
		this.carreras = carreras;
	}

}
