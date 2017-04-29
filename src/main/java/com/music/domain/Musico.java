package com.music.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Musico implements Serializable{
	private Integer identificador;
	private String nombre;
	private Integer seguidores;	
	private String imagen;	
	private String de;
	private String ruta;
	private String pistas;
	public Integer getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getSeguidores() {
		return seguidores;
	}
	public void setSeguidores(Integer seguidores) {
		this.seguidores = seguidores;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getPistas() {
		return pistas;
	}
	public void setPistas(String pistas) {
		this.pistas = pistas;
	}
	@Override
	public String toString() {
		return "Musico [identificador=" + identificador + ", nombre=" + nombre
				+ ", seguidores=" + seguidores + ", imagen=" + imagen + ", de="
				+ de + ", ruta=" + ruta + ", pistas=" + pistas + "]";
	}
}
