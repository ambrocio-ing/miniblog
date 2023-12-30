package com.amegdev.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.amegdev.dao.IPersonaDAO;
import com.amegdev.model.Persona;
import com.amegdev.model.Usuario;

@Stateless //@Named
public class PersonaDaoImpl implements IPersonaDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;

//	private EntityManagerFactory emf;
//	private EntityManager em;
//	
//	@PostConstruct
//	public void init() {
//		emf = Persistence.createEntityManagerFactory("blogPU");
//		em = emf.createEntityManager();
//	}
	
	@Override
	public Integer registrar(Persona t) throws Exception {		
//		try {
//			em.getTransaction().begin();
//			em.persist(t);
//			em.getTransaction().commit();
//		}
//		catch(Exception e) {
//			em.getTransaction().rollback();
//		}
		
		em.persist(t);
		return t.getId_persona();
	}

	@Override
	public Integer modificar(Persona t) throws Exception {
		em.merge(t);
		return t.getId_persona();
	}

	@Override
	public Integer eliminar(Persona t) throws Exception {
		em.remove(t);
		return 1;
	}

	@Override
	public List<Persona> listar() throws Exception {
		List<Persona> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT p FROM Persona p");
		lista = (List<Persona>) query.getResultList();
		
		return lista;
	}

	@Override
	public Persona obtenerPorId(Persona t) throws Exception {
		Persona per = new Persona();
		List<Persona> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT p FROM Persona p WHERE p.id_persona = ?1");
		query.setParameter(1, t.getId_persona());
		lista = (List<Persona>) query.getResultList();
		
		if(lista != null && !lista.isEmpty())
			per = lista.get(0);
		
		return per;
	}

}
