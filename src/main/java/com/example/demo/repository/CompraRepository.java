package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
	public int save(long id, long user, ProductoDTO dto, LocalDateTime fecha) {
	
		return jdbc.update("insert into compre(id, usuario, articulo, cantidad, fecha)"
				, id, user, dto.getId(), dto.getCantidad(), fecha);
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
		
		
		
		return null;
	}

	@Override
	public List<Compra> findAllByUsuario(long user) {
		return null;
	}

	@Override
	public int deleteById(long id, long user, HashSet<ProductoDTO> productos) {
		return 0;
	}

}
