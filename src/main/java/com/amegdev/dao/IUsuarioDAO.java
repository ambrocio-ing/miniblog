package com.amegdev.dao;

import java.util.List;

import javax.ejb.Local;

import com.amegdev.dto.PublicacionDTO;
import com.amegdev.model.Usuario;

@Local
public interface IUsuarioDAO extends ICrudDAO<Usuario> {
	
	Usuario traerPassHashed(String us) throws Exception;
	Usuario leerPorNombreUsuario(String us) throws Exception;
	List<Usuario> leerPorNombreUsuarioLike(String us) throws Exception;
	List<PublicacionDTO> publicacionesExport(String us) throws Exception;
}
