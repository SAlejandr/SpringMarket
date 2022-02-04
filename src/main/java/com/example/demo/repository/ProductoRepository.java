package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{


	public List<Producto> findByTituloContains(String patronTitulo);
	
	
	
}
