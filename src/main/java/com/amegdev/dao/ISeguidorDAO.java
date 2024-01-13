package com.amegdev.dao;

import java.util.List;

import javax.ejb.Local;

import com.amegdev.model.Persona;
import com.amegdev.model.PublicadorSeguidor;

@Local
public interface ISeguidorDAO {
	
	Integer registrarPublicadoresSeguidores(List<PublicadorSeguidor> publicadores_seguidores);
	List<PublicadorSeguidor> listarSeguidores(Persona per);	
	Integer dejarSeguir(List<PublicadorSeguidor> publicadores_seguidores);	
	//List<ReporteSeguidor> listarSeguidores();
	List<PublicadorSeguidor> listarSeguidos(Persona per);
}
