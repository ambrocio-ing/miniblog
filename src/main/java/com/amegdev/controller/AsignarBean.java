package com.amegdev.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import com.amegdev.model.Persona;
import com.amegdev.model.Rol;
import com.amegdev.model.Usuario;
import com.amgdev.service.IPersonaService;
import com.amgdev.service.IRolService;

@Named
@ViewScoped
public class AsignarBean implements Serializable {

	@Inject
	private IPersonaService pservice;
	
	@Inject
	private IRolService rservice;
	
	private List<Persona> personas;	
	private Persona persona;
	private DualListModel<Rol> dual;
	
	@PostConstruct
	public void init() {
		this.listarPersonas();
		this.listarRoles();
	}
	
	public void asignar() {
		try {
			Usuario us = new Usuario();
			us.setId(this.persona.getId_persona());
			us.setPersona(persona);
			rservice.asignar(us, this.dual.getTarget());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarRoles() {
		try {
			List<Rol> roles = this.rservice.listar();
			this.dual = new DualListModel<>(roles, new ArrayList<Rol>());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarPersonas() {
		try {
			this.personas = this.pservice.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public DualListModel<Rol> getDual() {
		return dual;
	}

	public void setDual(DualListModel<Rol> dual) {
		this.dual = dual;
	}
	
}
