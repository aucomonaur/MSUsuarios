package com.aucomonaur.modelo;

public class LfacultadCarrera {
	private String nombreCarrera;
	private long idFacultad;

	public LfacultadCarrera(String nombreCarrera, long idFacultad) {
		this.nombreCarrera = nombreCarrera;
		this.idFacultad = idFacultad;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public long getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(long idFacultad) {
		this.idFacultad = idFacultad;
	}

}
