package com.amgdev.service;

import java.util.List;

import com.amegdev.model.Persona;
import com.amegdev.model.Publicacion;

public interface IPublicacionService extends ICrudService<Publicacion> {
	List<Publicacion> listarPublicacionesPorPublicador(Persona persona) throws Exception;
	List<Publicacion> listarPublicacionesDeSeguidores(Persona persona) throws Exception;
}
