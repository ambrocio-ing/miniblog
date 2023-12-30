package com.amegdev.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.amegdev.model.Usuario;
import com.amgdev.service.IUsuarioService;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	@Inject
	private IUsuarioService usuarioService;
	
	private Usuario us;
	
	@PostConstruct
	public void init() {
		this.us = new Usuario();
	}
	
	public String login() {
		String redireccion = "";
		
		try {
			
			Usuario usuario = usuarioService.login(us);
			if(usuario != null && usuario.getId() != null && usuario.getEstado().equals("1")) {
				//Almacenar en la sessoin de jsf
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
				redireccion = "/protegido/roles?faces-redirect=true";
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","Credenciales incorrectas"));
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", e.getMessage()));
		}
		
		return redireccion;
	}

	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}
	
}
