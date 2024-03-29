package com.amegdev.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.amegdev.dao.IUsuarioDAO;
import com.amegdev.dto.PublicacionDTO;
import com.amegdev.model.Usuario;

@Stateless
public class UsuarioDaoImpl implements IUsuarioDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	
	@Override
	public Integer registrar(Usuario t) throws Exception {
		em.persist(t);
		return t.getPersona().getId_persona();
	}

	@Override
	public Integer modificar(Usuario t) throws Exception {
		em.merge(t);
		return t.getPersona().getId_persona();
	}

	@Override
	public Integer eliminar(Usuario t) throws Exception {
		em.remove(em.merge(t));
		return 1;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT u FROM Usuario u");
		lista = (List<Usuario>) query.getResultList();
		
		return lista;
	}

	@Override
	public Usuario obtenerPorId(Usuario t) throws Exception {
		Usuario us = new Usuario();
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id = ?1");
		query.setParameter(1, t.getPersona().getId_persona());
		lista = (List<Usuario>) query.getResultList();
		
		if(lista != null && !lista.isEmpty())
			us = lista.get(0);
		
		return us;
	}

	@Override
	public Usuario traerPassHashed(String us) throws Exception {
		Usuario usuario = null;
		Query query = em.createQuery("FROM Usuario u WHERE u.usuario = ?1");
		query.setParameter(1, us);
		
		List<Usuario> lista = query.getResultList();
		if(lista != null && !lista.isEmpty())
			usuario = lista.get(0);
		
		return usuario != null ? usuario : new Usuario();
	}

	@Override
	public Usuario leerPorNombreUsuario(String us) throws Exception {
		Usuario usuario = new Usuario();
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = ?1");
		query.setParameter(1, us);
		lista = (List<Usuario>) query.getResultList();
		
		if(lista != null && !lista.isEmpty())
			usuario = lista.get(0);
		
		return usuario;
	}

	@Override
	public List<Usuario> leerPorNombreUsuarioLike(String us) throws Exception {
		
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario like ?1");
		query.setParameter(1, "%" + us + "%");
		lista = (List<Usuario>) query.getResultList();
		
		return lista;
	}

	@Override
	public List<PublicacionDTO> publicacionesExport(String us) throws Exception {
		List<PublicacionDTO> pubs = new ArrayList<>();
		Query query = em.createNativeQuery("call spListarPublicaciones_v2(?1)");
		query.setParameter(1, us);
		
		List<Object[]> objs = query.getResultList();
		
		if(objs != null && !objs.isEmpty()) {
			objs.forEach(obj -> {
				String nombres = obj[0] != null ? String.valueOf(obj[0]) : "";
				String apellidos = obj[0] != null ? String.valueOf(obj[1]) : "";
				String usuario = obj[2] != null ? String.valueOf(obj[2]): "";
				String cuerpo = obj[3] != null ? String.valueOf(obj[3]) : "";
				
				pubs.add(new PublicacionDTO(nombres, apellidos, usuario, cuerpo));
			});
		}		
				
		return pubs;
	}

}
