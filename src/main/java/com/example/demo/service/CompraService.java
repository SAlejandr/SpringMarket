package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.repository.CompraDao;

@Service
public class CompraService implements ICompraService {

	@Autowired
	private CompraDao dao;
	
	@Override
	public void guardarCompra(Compra c) {
		
		int idP = dao.count() + 1;
		dao.save(c.getUsuario().getId(), c.getFecha());
		
		
		c.getProductos().stream().forEach(p -> dao.saveArticle(idP, p));
		
	}

	@Override
	public List<Compra> listarComprasPorUsuario(long usuario) {
		
		ArrayList<Compra> lista = (ArrayList<Compra>) dao.findAllByUsuario(usuario);
		
		lista.stream().forEach(c -> {
			Set<ProductoDTO> set = dao.findListaById(c.getId());
			
			c.setProductos((HashSet<ProductoDTO>) set);
		});
		
		return lista;
	}

	@Override
	public void borrarUnArticulo(long idCompra, long articulo) {

		dao.deleteByProducto(idCompra, articulo);
		
	}

}
