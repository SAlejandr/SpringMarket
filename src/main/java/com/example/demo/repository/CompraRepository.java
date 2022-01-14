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

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public int saveArticle(long id, ProductoDTO dto) {
		// TODO Auto-generated method stub
		return jdbc.update("insert into listaCompra(id, articulo, cantidad) value (?,?,?)"
				,  id, dto.getId(), dto.getCantidad());
	}
	
	@Override
	public Set<ProductoDTO> findListaById(long id){
		
		Query query = this.em.createQuery("select articulo, cantidad from listaCompra where id = :id");
		query.setParameter("id", id);
		
		
		List<ProductoDTO> lista = query.getResultList();//jdbc.query("select articulo, cantidad from listaCompra where id = ?" , (rs, rowNum) -> new ProductoDTO(rs.getLong("articulo"), "", rs.getInt("cantidad"), 0), id);
		
		HashSet<ProductoDTO> set = new HashSet<>();
		
		lista.stream().forEach(set::add);
		
		return set;
	}

	@Override
	public List<Compra> findAllByUsuario(long user) {
		Usuario u = new Usuario();
		u.setId(user);
		return jdbc.query("select * from compra where usuario = ?",(rs, rowNum) -> new Compra(rs.getLong("id"),u), user);
		
	}
}
