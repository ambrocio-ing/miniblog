package com.amegdev.util;

import java.io.Serializable;

public class ReporteSeguidor implements Serializable {

	private int cantidad;
	private String persona;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

}
