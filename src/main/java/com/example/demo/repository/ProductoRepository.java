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
		
		
		
		return jdbc.update("");
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Producto> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Producto producto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
