package com.example.demo.repository;

import java.time.*;
import java.util.*;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.pojos.Usuario;
@Repository
public class CompraRepository extends DaoRepository<Compra> implements CompraDao {
	
	@Override
	public Set<ProductoDTO> findListaById(long id){
		
		Query query = this.em.createQuery("select articulo, cantidad from listaCompra where id = :id");
		query.setParameter("id", id);
		
		List<ProductoDTO> lista = query.getResultList();
		
		HashSet<ProductoDTO> set = new HashSet<>();
		
		lista.stream().forEach(set::add);
		
		return set;
	}

	@Override
	public List<Compra> findAllByUsuario(long user) {
		Usuario u = new Usuario();
		u.setId(user);
		Query query = this.em.createQuery("from Compra c where c.usuario = :usuario");
		query.setParameter("usuario", u);
		
		return query.getResultList();
		
	}

	@Override
	public Compra findUlitmaByUsuario(long user) {
		Usuario u = new Usuario();
		u.setId(user);
		Query query = this.em.createQuery("from Compra c where c.usuario = :usuario order by c.id desc");
		query.setParameter("usuario", u);
		
		
		return (Compra) query.getSingleResult();
	}
}
