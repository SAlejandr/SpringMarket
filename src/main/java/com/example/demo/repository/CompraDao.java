package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;

public interface CompraDao {
	
	public int save(long user, LocalDateTime fecha);
	public int saveArticle(long id, ProductoDTO dto);
	public int count();
	public Optional<Compra> findById(long id);
	public Set<ProductoDTO> findListaById(long id);
	public List<Compra> findAllByUsuario(long user);
	public int deleteByProducto(long id, long articulo );
	
}
