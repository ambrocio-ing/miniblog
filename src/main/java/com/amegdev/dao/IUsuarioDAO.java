package com.amegdev.dao;

import javax.ejb.Local;

import com.amegdev.model.Usuario;

@Local
public interface IUsuarioDAO extends ICrudDAO<Usuario> {
	
	Usuario traerPassHashed(String us) throws Exception;
	Usuario leerPorNombreUsuario(String us) throws Exception;
}
