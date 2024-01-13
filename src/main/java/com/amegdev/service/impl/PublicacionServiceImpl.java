package com.amegdev.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.amegdev.dao.IPublicacionDAO;
import com.amegdev.model.Mencion;
import com.amegdev.model.Persona;
import com.amegdev.model.Publicacion;
import com.amegdev.model.Tag;
import com.amgdev.service.IPublicacionService;

@Named
public class PublicacionServiceImpl implements IPublicacionService, Serializable {

	@EJB
	private IPublicacionDAO dao;
	
	@Override
	public Integer registrar(Publicacion t) throws Exception {
		List<Tag> tags = new ArrayList<>();
		List<Mencion> menciones = new ArrayList<>();
		
		String texto = t.getCuerpo();
		texto = texto.replace(",", "");
		String[] arreglo = texto.split(" ");
		
		for(String x : arreglo) {
			if(x.startsWith("@")) {
				x = x.substring(1, x.length());
				menciones.add(new Mencion(x, t));
			}
			
			if(x.startsWith("#")) {
				x = x.substring(1, x.length());
				tags.add(new Tag(x, t));
			}
		}
		
		t.setTags(tags);
		t.setMenciones(menciones);
		
		return dao.registrar(t);
	}

	@Override
	public Integer modificar(Publicacion t) throws Exception {
		
		return null;
	}

	@Override
	public Integer eliminar(Publicacion t) throws Exception {
		
		return null;
	}

	@Override
	public List<Publicacion> listar() throws Exception {
		
		return null;
	}

	@Override
	public Publicacion obtenerPorId(Publicacion t) throws Exception {
		
		return null;
	}

	@Override
	public List<Publicacion> listarPublicacionesPorPublicador(Persona persona) throws Exception {
		
		return dao.listarPublicacionesPorPublicador(persona);
	}

	@Override
	public List<Publicacion> listarPublicacionesDeSeguidores(Persona persona) throws Exception {
		
		return dao.listarPublicacionesDeSeguidores(persona);
	}

}
