package com.amgdev.service;

import com.amegdev.model.Usuario;

public interface IUsuarioService extends ICrudService<Usuario> {

	Usuario login(Usuario us) throws Exception;
	
}
