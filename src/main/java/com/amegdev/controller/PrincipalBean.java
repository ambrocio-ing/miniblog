package com.amegdev.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.amegdev.model.Publicacion;
import com.amegdev.model.Usuario;
import com.amgdev.service.IPublicacionService;

@Named
@ViewScoped
public class PrincipalBean implements Serializable {

	@Inject
	private IPublicacionService pservice;
	
	private List<Publicacion> publicaciones;
	private Usuario us;
	
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		this.listarPublicaciones();
	}
	
	public void listarPublicaciones() {
		try {
			this.publicaciones = this.pservice.listarPublicacionesDeSeguidores(us.getPersona());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String hola = "hola";
		
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
	
}
