package com.amegdev.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.amegdev.dao.IUsuarioDAO;
import com.amegdev.model.Usuario;
import com.amgdev.service.IUsuarioService;

@Named
public class UsuarioServiceImpl implements IUsuarioService, Serializable {

	@EJB
	private IUsuarioDAO dao;
	
	@Override
	public Integer registrar(Usuario t) throws Exception {
		
		return dao.registrar(t);
	}

	@Override
	public Integer modificar(Usuario t) throws Exception {
		
		return dao.modificar(t);
	}

	@Override
	public Integer eliminar(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtenerPorId(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario login(Usuario us) throws Exception {
		
		String clave = us.getContrasena();
		Usuario UsuarioHash = dao.traerPassHashed(us.getUsuario());	
				
		if(UsuarioHash.getId() != null && BCrypt.checkpw(clave, UsuarioHash.getContrasena())) {
			UsuarioHash.setContrasena("");
			return UsuarioHash;
		}			
		
		return new Usuario();
	}

}
