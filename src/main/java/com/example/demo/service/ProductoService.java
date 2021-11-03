package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojos.Producto;
import com.example.demo.repository.ProductoDao;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private ProductoDao dao;
	
	@Override
	public int guardar(Producto producto) {
		// TODO Auto-generated method stub
		
		return dao.save(producto);
	}

	@Override
	public ArrayList<Producto> listarTodas() {
		// TODO Auto-generated method stub
		return (ArrayList<Producto>) dao.findAll();
	}

	@Override
	public Producto buscarPorId(long id) {
		// TODO Auto-generated method stub
	
		Optional<Producto> optional = dao.findById(id);
		
		return optional.orElse(new Producto(-1, "", "", 0, 0));
	}

	@Override
	public ArrayList<Producto> buscarPorPatronDeTutulo(String patron) {
		// TODO Auto-generated method stub
		
		return (ArrayList<Producto>) dao.findAllByTituloLike(patron);
	}

	@Override
	public Producto borrarPorId(long id) {
		// TODO Auto-generated method stub
		
		
		Optional<Producto> optional = dao.findById(id);
		
		if(optional.isPresent()) {
			
			dao.delete(optional.get());
			
		}
		
		return optional.orElse(new Producto(-1, "", "", 0, 0));
	}

}
