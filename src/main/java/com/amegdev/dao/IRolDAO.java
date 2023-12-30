package com.amegdev.dao;

import java.util.List;

import javax.ejb.Local;

import com.amegdev.model.Rol;
import com.amegdev.model.Usuario;
import com.amegdev.model.UsuarioRol;

@Local
public interface IRolDAO extends ICrudDAO<Rol>{

	Integer asignar(Usuario us, List<UsuarioRol> urs) throws Exception;
	
}
