package com.amegdev.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.amegdev.dao.IRolDAO;
import com.amegdev.model.Rol;
import com.amegdev.model.Usuario;
import com.amegdev.model.UsuarioRol;

@Stateless
public class RolDaoImpl implements IRolDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	
	@Override
	public Integer registrar(Rol t) throws Exception {
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer modificar(Rol t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer eliminar(Rol t) throws Exception {
		em.remove(em.merge(t));
		return 1;
	}

	@Override
	public List<Rol> listar() throws Exception {
		Query query = em.createQuery("SELECT r FROM Rol r"); //JPQL
		List<Rol> lista = (List<Rol>) query.getResultList();
		return lista;
	}

	@Override
	public Rol obtenerPorId(Rol t) throws Exception {
		Query query = em.createQuery("SELECT r FROM Rol r WHERE r.id = ?1"); //JPQL
		query.setParameter(1, t.getId());
		List<Rol> lista = (List<Rol>) query.getResultList();
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Rol();
	}

	@Override
	public Integer asignar(Usuario us, List<UsuarioRol> urs) throws Exception{
		Query query = em.createNativeQuery("delete from usuario_rol ur where ur.usuario_id = ?1");
		query.setParameter(1, us.getPersona().getId_persona());
		query.executeUpdate();
		
		int[] i = { 0 };
		urs.forEach(ur -> {			
			em.persist(ur);
			
			if(i[0] % 100 == 0) {
				em.flush();
				em.clear();
			}
			i[0]++;			
		});
		
		return i[0];
	}

}
