package com.amegdev.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import com.amegdev.dao.IPersonaDAO;
//import com.amegdev.dao.impl.PersonaDaoImpl;
import com.amegdev.model.Persona;
import com.amgdev.service.IPersonaService;

@Named
public class PersonaServiceImpl implements IPersonaService, Serializable {

	@EJB //@Inject
	private IPersonaDAO dao;
	
//	public PersonaServiceImpl() {
//		dao = new PersonaDaoImpl();
//	}
	
	@Override
	public Integer registrar(Persona t) throws Exception {
		
		return dao.registrar(t);
	}

	@Override
	public Integer modificar(Persona t) throws Exception {
		
		return dao.modificar(t);
	}

	@Override
	public Integer eliminar(Persona t) throws Exception {
		
		return dao.eliminar(t);
	}

	@Override
	public List<Persona> listar() throws Exception {
		
		return dao.listar();
	}

	@Override
	public Persona obtenerPorId(Persona t) throws Exception {
		
		return dao.obtenerPorId(t);
	}

}
