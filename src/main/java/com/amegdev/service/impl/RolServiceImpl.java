package com.amegdev.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.amegdev.dao.IRolDAO;
import com.amegdev.model.Rol;
import com.amegdev.model.Usuario;
import com.amegdev.model.UsuarioRol;
import com.amgdev.service.IRolService;

@Named
public class RolServiceImpl implements IRolService, Serializable {
	
	@EJB
	private IRolDAO dao;

	@Override
	public Integer registrar(Rol t) throws Exception {
		
		return dao.registrar(t);
	}

	@Override
	public Integer modificar(Rol t) throws Exception {
	
		return dao.modificar(t);
	}

	@Override
	public Integer eliminar(Rol t) throws Exception {
	
		return dao.eliminar(t);
	}

	@Override
	public List<Rol> listar() throws Exception {
		
		return dao.listar();
	}

	@Override
	public Rol obtenerPorId(Rol t) throws Exception {
		
		return dao.obtenerPorId(t);
	}

	@Override
	public Integer asignar(Usuario us, List<Rol> roles)  throws Exception {
		
		List<UsuarioRol> urs = new ArrayList<>();
		roles.forEach(r -> {
			UsuarioRol ur = new UsuarioRol();
			ur.setUsurio(us);
			ur.setRol(r);
			urs.add(ur);
		});
		
		return dao.asignar(us, urs);
	}

}
