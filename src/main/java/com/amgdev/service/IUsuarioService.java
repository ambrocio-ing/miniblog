package com.amgdev.service;

import java.util.List;

import com.amegdev.dto.PublicacionDTO;
import com.amegdev.model.Usuario;

public interface IUsuarioService extends ICrudService<Usuario> {

	Usuario login(Usuario us) throws Exception;
	List<Usuario> leerPorNombreUsuarioLike(String us) throws Exception;
	List<PublicacionDTO> publicacionesExport(String us) throws Exception;
}
