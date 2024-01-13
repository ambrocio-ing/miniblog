package com.amegdev.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.amegdev.dao.IPublicacionDAO;
import com.amegdev.model.Persona;
import com.amegdev.model.Publicacion;
import com.amegdev.model.PublicadorSeguidor;

@Stateless
public class PublicacionDaoImpl implements IPublicacionDAO, Serializable{

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	
	@Override
	public Integer registrar(Publicacion t) throws Exception {
		em.persist(t);
		em.flush();
		return t.getId() != null ? t.getId() : 0;
	}

	@Override
	public Integer modificar(Publicacion t) throws Exception {
		
		return null;
	}

	@Override
	public Integer eliminar(Publicacion t) throws Exception {
		
		return null;
	}

	@Override
	public List<Publicacion> listar() throws Exception {
		
		return null;
	}

	@Override
	public Publicacion obtenerPorId(Publicacion t) throws Exception {
		
		return null;
	}

	@Override
	public List<Publicacion> listarPublicacionesPorPublicador(Persona persona) throws Exception {
		List<Publicacion> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT p FROM Publicacion p WHERE p.persona.id_persona = ?1");
		query.setParameter(1, persona.getId_persona());
		lista = (List<Publicacion>) query.getResultList();
		return lista;
	}

	@Override
	public List<Publicacion> listarPublicacionesDeSeguidores(Persona persona) throws Exception {
		
		List<PublicadorSeguidor> listaSeguidores = new ArrayList<>();
		List<Publicacion> listaPublicaciones = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM PublicadorSeguidor p where p.seguidor.id_persona =?1");
			query.setParameter(1, persona.getId_persona());

			listaSeguidores = (List<PublicadorSeguidor>) query.getResultList();
			
			listaSeguidores.forEach(s -> {
				Query q = em.createQuery("FROM Publicacion pu where pu.persona.id_persona =?1");
				q.setParameter(1, s.getPublicador().getId_persona());	
				List<Publicacion> lista = (List<Publicacion>)q.getResultList();
				listaPublicaciones.addAll(lista);
			});								

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPublicaciones;
	}	
	
}
