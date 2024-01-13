package com.amegdev.dao;

import java.util.List;

import javax.ejb.Local;

import com.amegdev.model.Persona;
import com.amegdev.model.Publicacion;

@Local
public interface IPublicacionDAO extends ICrudDAO<Publicacion> {

	List<Publicacion> listarPublicacionesPorPublicador(Persona persona) throws Exception;
	List<Publicacion> listarPublicacionesDeSeguidores(Persona persona) throws Exception;
	
}
