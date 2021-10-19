package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojos.Producto;

public interface ProductoDao{

	public int save(Producto producto);
	public int count();
	public List<Producto> findAll();
	public Optional<Producto> findById(long id);
	public int delete(Producto producto);
}
