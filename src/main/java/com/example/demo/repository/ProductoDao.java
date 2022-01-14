package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojos.Producto;

public interface ProductoDao extends DaoGenerico<Producto>{

	public List<Producto> findAll();
	public List<Producto> findAllByTituloLike(String patronTitulo);
}
