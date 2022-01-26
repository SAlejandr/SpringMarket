package com.example.demo.repository;

import java.time.*;
import java.util.*;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.pojos.Usuario;
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	//public Set<Compra> findListaById(long id);
	public List<Compra> findByUsuario(Usuario Usuario);
	public Compra findTopByUsuarioOrderByIdDesc(Usuario user);
}
