package com.amegdev.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.amegdev.model.Persona;
import com.amegdev.model.Rol;
import com.amegdev.model.Usuario;
import com.amgdev.service.IPersonaService;
import com.amgdev.service.IRolService;

@Named
@ViewScoped
public class RegistroBean implements Serializable {
	
	@Inject
	private IPersonaService personaService;
	
	@Inject
	private IRolService rolService;
	
	private Persona persona;
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		this.persona = new Persona();
		this.usuario = new Usuario();
	}
	
	
	public String registrar() {
		String redireccion = "";
		try {
			String clave = this.usuario.getContrasena();
			String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());
			this.usuario.setContrasena(claveHash);
			
			this.persona.setUsuario(usuario);
			this.usuario.setPersona(this.persona);
			this.personaService.registrar(persona);
			
			List<Rol> roles = new ArrayList<>();
			Rol r = new Rol();
			r.setId(1);
			roles.add(r);
			
			rolService.asignar(this.usuario, roles);
			
			redireccion = "index?faces-redirect=true";			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return redireccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
