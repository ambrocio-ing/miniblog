package com.amgdev.service;

import java.util.List;

import com.amegdev.model.Rol;
import com.amegdev.model.Usuario;

public interface IRolService extends ICrudService<Rol> {
	
	Integer asignar(Usuario us, List<Rol> roles) throws Exception;
	
}
