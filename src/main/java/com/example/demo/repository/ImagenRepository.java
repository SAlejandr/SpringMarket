package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojos.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Long>{

	public List<Imagen> findByNombreContains(String nombre);
}
