package com.amegdev.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.amegdev.dao.ISeguidorDAO;
import com.amegdev.model.Persona;
import com.amegdev.model.PublicadorSeguidor;

@Stateless
public class SeguidorDaoImpl implements ISeguidorDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	
	@Override
	public Integer registrarPublicadoresSeguidores(List<PublicadorSeguidor> publicadores_seguidores) {
		try {
			
			int[] i = {0};
			publicadores_seguidores.forEach(ps -> {
				em.persist(ps);
				if(i[0] % 100 == 0) {
					em.flush();
					em.clear();
				}				
				i[0]++;				
			});
						
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<PublicadorSeguidor> listarSeguidores(Persona per) {
		List<PublicadorSeguidor> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM PublicadorSeguidor p where p.publicador.id_persona =?1");
			query.setParameter(1, per.getId_persona());

			lista = (List<PublicadorSeguidor>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Integer dejarSeguir(List<PublicadorSeguidor> publicadores_seguidores) {
		
		int rpta = 0;
		try {

			publicadores_seguidores.forEach(ps -> {
				Query query = em.createQuery(
						"DELETE FROM PublicadorSeguidor WHERE publicador.id_persona =?1 AND seguidor.id_persona = ?2");
				query.setParameter(1, ps.getPublicador().getId_persona());
				query.setParameter(2, ps.getSeguidor().getId_persona());

				query.executeUpdate();
			});
			rpta = 1;

		} catch (Exception e) {
			rpta = 0;
		}
		return rpta;
	}

	@Override
	public List<PublicadorSeguidor> listarSeguidos(Persona per) {
		List<PublicadorSeguidor> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM PublicadorSeguidor p where p.seguidor.id_persona =?1");
			query.setParameter(1, per.getId_persona());

			lista = (List<PublicadorSeguidor>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	
	}	
	
}
