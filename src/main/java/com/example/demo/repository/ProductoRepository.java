package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Producto;

@Repository
public class ProductoRepository extends DaoRepository<Producto> implements ProductoDao{

	@Autowired
	private JdbcTemplate jdbc;


	@Override
	public List<Producto> findAll() {
		
		Query query = this.em.createQuery("FROM Producto");
		
		return query.getResultList();
		
		
	}

	@Override
	public List<Producto> findAllByTituloLike(String patronTitulo) {
		
		Query query = this.em.createQuery("FROM Producto p where p.titulo like :patron");
		query.setParameter("patron", "%"+patronTitulo+"%");
		
		
		return query.getResultList();
	}
	
	
	
}
