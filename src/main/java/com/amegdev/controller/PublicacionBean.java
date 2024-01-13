package com.amegdev.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.amegdev.model.Persona;
import com.amegdev.model.Publicacion;
import com.amegdev.model.Usuario;
import com.amgdev.service.IPublicacionService;

@Named
@ViewScoped
public class PublicacionBean implements Serializable {

	@Inject
	private IPublicacionService pservice;
	
	@Inject
	private PushBean push;
	
	private Publicacion publicacion;
	private Usuario us;
	List<Publicacion> publicaciones;

	@PostConstruct
	public void init() {
		this.publicacion = new Publicacion();
		this.us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.listarPublicaciones();
	}

	public void publicar() {
		try {
			Persona per = new Persona();
			per.setId_persona(this.us.getId());
			this.publicacion.setPersona(per);
			this.pservice.registrar(publicacion);
			
			push.sendMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarPublicaciones() {
		try {
			this.publicaciones = this.pservice.listarPublicacionesPorPublicador(this.us.getPersona());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

}
