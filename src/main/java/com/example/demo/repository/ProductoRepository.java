package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Producto;

@Repository
public class ProductoRepository implements ProductoDao{

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public int save(Producto producto) {
		// TODO Auto-generated method stub
		

		return jdbc.update("insert into producto(titulo, descripcion,precio, descuento) values (?,?,?,?)", producto.getTitulo(), producto.getDescripcion(),
				producto.getPrecio(), producto.getDescuento());
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		Integer cantidad = jdbc.queryForObject("select count(*) as 'cantidad' from producto", Integer.class);
		return 0;
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		
		return jdbc.query("select * from producto", (rs, rowNum) -> new Producto(rs.getLong("id"), rs.getString("titulo"), 
				rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("descuento")));
	}


	
	@SuppressWarnings("deprecation")
	@Override
	public Optional<Producto> findById(long id) {
		return jdbc.queryForObject("select * from producto where id = ?", new Object[] { id }, (rs,
				rowNum) -> Optional.of(new Producto(rs.getLong("id"), rs.getString("titulo"), 
						rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("descuento"))));
	}

	@Override
	public int delete(Producto producto) {
		// TODO Auto-generated method stub
		
		
		return jdbc.update("delete * from producto where id = ?", producto.getId());
	}
	
	
	
}
