package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;

public interface CompraDao {
	
	public int save(long id, long user, ProductoDTO dto, LocalDateTime fecha);
	public int count();
	public Optional<Compra> findById(long id);
	public List<Compra> findAllByUsuario(long user);
	public int deleteById(long id, long user, HashSet<ProductoDTO> productos );
	
}
