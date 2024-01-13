package com.amegdev.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.amegdev.model.Persona;
import com.amgdev.service.IPersonaService;

@Named
@ViewScoped
public class PersonaBean implements Serializable {
	
	@Inject
	private IPersonaService pservice;
	
	private Persona persona;
	private List<Persona> lista;
	
	private UploadedFile foto;
	private String tipoDialog;
	
	public PersonaBean() {
		this.persona = new Persona();
		//this.pservice = new PersonaServiceImpl();
	}
	
	@PostConstruct
	public void init() {
		this.listar();
		this.tipoDialog = "Nuevo";
	}
	
	public void limpiarControles() {
		this.persona = new Persona();
		this.tipoDialog = "Nuevo";
	}
	
	public void mostrarData(Persona p) {
		this.persona = p;
		this.tipoDialog = "Modificar";
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (event.getFile() != null) {
				this.persona.setFoto(event.getFile().getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void operar(String accion) {
		try {
			
			if(foto != null) {
				this.persona.setFoto(foto.getContent());
			}
			
			if(accion.equalsIgnoreCase("R")) {
				this.pservice.registrar(persona);
				
			}
			else if(accion.equalsIgnoreCase("M")) {
				this.pservice.modificar(persona);
			}	
			
			this.listar();
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listar() {
		try {
			this.lista = pservice.listar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public List<Persona> getLista() {
		return lista;
	}


	public void setLista(List<Persona> lista) {
		this.lista = lista;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public String getTipoDialog() {
		return tipoDialog;
	}

	public void setTipoDialog(String tipoDialog) {
		this.tipoDialog = tipoDialog;
	}
	
}
