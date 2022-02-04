package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojos.Producto;
import com.example.demo.repository.ProductoRepository;
@Transactional
@Service
public class ProductoService implements IProductoService {

	@Autowired
	private ProductoRepository dao;
	
	@Override
	public Producto guardar(Producto producto) {
		
		return dao.save(producto);
	}

	@Override
	public ArrayList<Producto> listarTodas() {
		return (ArrayList<Producto>) dao.findAll();
	}

	@Override
	public Producto buscarPorId(Long id) {
	
		Optional<Producto> optional = dao.findById(id);
		
		return optional.orElse(new Producto());
	}

	@Override
	public ArrayList<Producto> buscarPorPatronDeTitulo(String patron) {
		
		return (ArrayList<Producto>) dao.findByTituloContains(patron);
	}

	@Override
	public Producto borrarPorId(Long id) {
		
		
		Optional<Producto> optional = dao.findById(id);
		
		if(optional.isPresent()) {
			
			dao.delete(optional.get());
			
		}
		
		return optional.orElse(new Producto());
	}

}
