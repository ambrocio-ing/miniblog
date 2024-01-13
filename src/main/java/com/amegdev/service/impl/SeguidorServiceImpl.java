package com.amegdev.service.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.amegdev.dao.ISeguidorDAO;
import com.amegdev.model.Persona;
import com.amegdev.model.PublicadorSeguidor;
import com.amgdev.service.ISeguidorService;

@Named
public class SeguidorServiceImpl implements ISeguidorService, Serializable {

	@EJB
	private ISeguidorDAO dao;

	@Override
	public Integer registrarPublicadoresSeguidores(List<Persona> seguidores, List<Persona> publicadores) {
		
		List<PublicadorSeguidor> pss = new ArrayList<>();
		try {
			publicadores.forEach(p -> {
				seguidores.forEach(s -> {
					PublicadorSeguidor ps = new PublicadorSeguidor();
					ps.setPublicador(p);
					ps.setSeguidor(s);
					ps.setFecha(LocalDateTime.now());
					
					pss.add(ps);
				});
			});
			
			dao.registrarPublicadoresSeguidores(pss);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}

	@Override
	public List<PublicadorSeguidor> listarSeguidores(Persona per) {
		
		return dao.listarSeguidores(per);
	}

	@Override
	public Integer dejarSeguir(List<Persona> seguidores, List<Persona> publicadores) {
		List<PublicadorSeguidor> pss = new ArrayList<>();
		try {
			publicadores.forEach(p -> {
				seguidores.forEach(s -> {
					PublicadorSeguidor ps = new PublicadorSeguidor();
					ps.setPublicador(p);
					ps.setSeguidor(s);
					ps.setFecha(LocalDateTime.now());
					
					pss.add(ps);
				});
			});
			
			dao.dejarSeguir(pss);
		} catch (Exception e) {
			return 0;
		}
		
		return 1;
	}

	@Override
	public List<PublicadorSeguidor> listarSeguidos(Persona per) {
		
		return dao.listarSeguidos(per);
	}
	
	

}
