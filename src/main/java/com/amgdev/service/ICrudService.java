package com.amgdev.service;

import java.util.List;

public interface ICrudService<T> {

	Integer registrar(T t) throws Exception;
	Integer modificar(T t) throws Exception;
	Integer eliminar(T t) throws Exception;
	List<T> listar() throws Exception;
	T obtenerPorId(T t) throws Exception;
	
}
