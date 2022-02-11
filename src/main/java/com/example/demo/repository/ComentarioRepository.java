package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	
	
}
