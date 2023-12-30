package com.amegdev.dao;

import java.util.List;

public interface ICrudDAO<T> {

	Integer registrar(T t) throws Exception;
	Integer modificar(T t) throws Exception;
	Integer eliminar(T t) throws Exception;
	List<T> listar() throws Exception;
	T obtenerPorId(T t) throws Exception;
}
