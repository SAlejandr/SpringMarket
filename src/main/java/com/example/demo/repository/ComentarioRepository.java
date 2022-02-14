package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Comentario;
import com.example.demo.pojos.Producto;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	 public List<Comentario> findByProducto(Producto producto);
	
}
