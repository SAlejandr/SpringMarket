package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pojos.Comentario;
import com.example.demo.pojos.Producto;
import com.example.demo.repository.ComentarioRepository;

public class ComentarioService implements IComentarioService{
	
	@Autowired
	private ComentarioRepository dao;

	@Override
	public Comentario guardarComentario(Comentario c) {
		
		return dao.save(c);
	}

	@Override
	public Optional<Comentario> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Comentario borrarPorId(Long id) {
		// TODO Auto-generated method stub

		Comentario c = dao.findById(id).orElse(new Comentario());
		
		if(dao.existsById(id)) {
			
			dao.deleteById(id);
		}
		
		return c;
	}

	@Override
	public boolean existenciaPorId(Long id) {
		// TODO Auto-generated method stub
		return dao.existsById(id);
	}

	@Override
	public List<Comentario> obtenerComentariosPorProducto(Producto p) {
		// TODO Auto-generated method stub
		return dao.findByProducto(p);
	}

}
