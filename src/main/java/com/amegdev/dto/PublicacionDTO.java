package com.amegdev.dto;

import java.io.Serializable;

public class PublicacionDTO implements Serializable {

	private String nombres;
	private String apellidos;
	private String usuario;
	private String cuerpo;

	public PublicacionDTO(String nombres, String apellidos, String usuario, String cuerpo) {
		
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.cuerpo = cuerpo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

}
