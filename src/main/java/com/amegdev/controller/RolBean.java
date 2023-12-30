package com.amegdev.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.amegdev.model.Rol;
import com.amgdev.service.IRolService;

@Named
@ViewScoped
public class RolBean implements Serializable {

	@Inject
	private IRolService rolService;
	
	private List<Rol> roles;
	
	@PostConstruct
	public void init() {
		this.listar();
	}
	
	public void listar() {
		try {
			roles = rolService.listar();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		try {
			this.rolService.modificar((Rol) event.getObject());
			FacesMessage msg = new FacesMessage("Se modificó", ((Rol) event.getObject()).getTipo());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}	
	
}
