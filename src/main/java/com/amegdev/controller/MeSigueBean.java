package com.amegdev.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.amegdev.model.PublicadorSeguidor;
import com.amegdev.model.Usuario;
import com.amgdev.service.ISeguidorService;

@Named
@ViewScoped
public class MeSigueBean implements Serializable{
	
	@Inject 
	private ISeguidorService sservice;
	
	private List<PublicadorSeguidor> seguidores;
	private Usuario us;
	
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		this.listarSeguidores();
	}
	
	public void listarSeguidores() {
		try {
			this.seguidores = this.sservice.listarSeguidores(this.us.getPersona());
		} catch (Exception e) {
			
		}
		
	}

	public List<PublicadorSeguidor> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<PublicadorSeguidor> seguidores) {
		this.seguidores = seguidores;
	}
	
}
