package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;

public interface CompraDao extends DaoGenerico<Compra>{
	
	public int saveArticle(long id, ProductoDTO dto);
	public Set<ProductoDTO> findListaById(long id);
	public List<Compra> findAllByUsuario(long user);
	
}
