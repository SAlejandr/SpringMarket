package com.example.demo.repository;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.pojos.Usuario;
@Repository
public class CompraRepository implements CompraDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Override
	public int save( long user, LocalDateTime fecha) {
	
		return jdbc.update("insert into compra(usuario, fecha) value (?,?)"
				,  user, fecha);
	}
	@Override
	public int saveArticle(long id, ProductoDTO dto) {
		// TODO Auto-generated method stub
		return jdbc.update("insert into listaCompra(id, articulo, cantidad) value (?,?,?)"
				,  id, dto.getId(), dto.getCantidad());
	}
	@Override
	public int count() {
		
		int cantidad = jdbc.queryForObject("select count(distinct id) as 'cantidad' from compra", Integer.class);
		return cantidad;
	}

	@Override
	public Optional<Compra> findById(long id) {
		
		
		
		Compra c = jdbc.queryForObject("select * from compra where id = ?", (rs, rowNum)-> {
			Usuario u = new Usuario();
			u.setId(rs.getLong("usuario"));
			 return new Compra(rs.getLong("id"), u);
		}, id);
		
		
		
		return Optional.of(c);
	}
	
	public Set<ProductoDTO> findListaById(long id){
		
		List<ProductoDTO> lista = jdbc.query("select articulo, cantidad from listaCompra where id = ?" , (rs, rowNum) -> new ProductoDTO(rs.getLong("articulo"), "", rs.getInt("cantidad"), 0), id);
		
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

	@Override
	public int deleteByProducto(long id,long articulo) {
		return jdbc.update("delete from listaCompra where id =? and articulo =?", id, articulo );
	}
}
