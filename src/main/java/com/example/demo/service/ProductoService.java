package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojos.Producto;
import com.example.demo.repository.ProductoDao;
@Transactional
@Service
public class ProductoService implements IProductoService {

	@Autowired
	private ProductoDao dao;
	
	@Override
	public Producto guardar(Producto producto) {
		
		return dao.crear(producto);
	}

	@Override
	public ArrayList<Producto> listarTodas() {
		return (ArrayList<Producto>) dao.findAll();
	}

	@Override
	public Producto buscarPorId(long id) {
	
		Optional<Producto> optional = Optional.of(dao.buscar(id));
		
		return optional.orElse(new Producto(-1, "", "", 0, 0));
	}

	@Override
	public ArrayList<Producto> buscarPorPatronDeTitulo(String patron) {
		
		return (ArrayList<Producto>) dao.findAllByTituloLike(patron);
	}

	@Override
	public Producto borrarPorId(long id) {
		
		
		Optional<Producto> optional = Optional.of(dao.buscar(id));
		
		if(optional.isPresent()) {
			
			dao.borrar(optional.get());
			
		}
		
		return optional.orElse(new Producto(-1, "", "", 0, 0));
	}

}
