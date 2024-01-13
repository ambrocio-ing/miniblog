package com.amegdev.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.amegdev.model.Persona;
import com.amegdev.model.PublicadorSeguidor;
import com.amegdev.model.Usuario;
import com.amegdev.util.MensajeManager;
import com.amgdev.service.IPersonaService;
import com.amgdev.service.ISeguidorService;

@Named
@ViewScoped
public class SeguirBean implements Serializable {

	@Inject
	private IPersonaService pservice;
	
	@Inject
	private ISeguidorService sservice;
	
	@Inject
	private MensajeManager mm;
	
	private Usuario us;
	private List<Persona> personas;
	List<PublicadorSeguidor> seguidos;
	
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		
		this.listarSeguidos();
		this.listarPersonas();		
	}
	
	public void listarPersonas() {
		try {
			this.personas = pservice.listar();
			
			this.personas.remove(this.us.getPersona());
			
			this.personas.forEach(p -> {
				this.seguidos.forEach(s -> {
					if(s.getPublicador().getId_persona() == p.getId_persona()) {
						p.setEsSeguido(true);
					}
				});				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarSeguidos() {
		try {
			this.seguidos = this.sservice.listarSeguidos(this.us.getPersona());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seguir(Persona aseguir) {
		try {
			List<Persona> seguidores = new ArrayList<>();
			List<Persona> publicadores = new ArrayList<>();
			seguidores.add(this.us.getPersona());
			publicadores.add(aseguir);
			
			this.sservice.registrarPublicadoresSeguidores(seguidores, publicadores);
			mm.mostrarMensaje("AVISO", "¡ Ahora sigues a " + aseguir.getNombres() + "!", "INFO");
		} catch (Exception e) {
			mm.mostrarMensaje("AVISO", "Error al seguir", "ERROR");
			e.printStackTrace();
		}
		finally {
			this.listarSeguidos();
			this.listarPersonas();
		}
		
	}
	
	public void dejar(Persona aDejar) {
		try {
			List<Persona> seguidores = new ArrayList<>();
			List<Persona> publicadores = new ArrayList<>();
			
			seguidores.add(this.us.getPersona());
			publicadores.add(aDejar);
			
			this.sservice.dejarSeguir(seguidores, publicadores);
			mm.mostrarMensaje("AVISO", "¡ Dejaste de seguir a " + aDejar.getNombres() + "!", "INFO");
		} catch (Exception e) {
			mm.mostrarMensaje("AVISO", "Error al dejar de seguir", "ERROR");
			e.printStackTrace();
		}
		finally {
			this.listarSeguidos();
			this.listarPersonas();
		}
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
}
