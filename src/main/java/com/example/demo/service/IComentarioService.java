package com.example.demo.service;

import java.util.Optional;

import com.example.demo.pojos.Comentario;

public interface IComentarioService {

	public Comentario guardarComentario(Comentario c);
	public Optional<Comentario> buscarPorId(Long id);
	public Comentario borrarPorId(Long id);
	public boolean existenciaPorId(Long id);
	
}
