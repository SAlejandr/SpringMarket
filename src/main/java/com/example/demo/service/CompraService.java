package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.pojos.IdItemCompra;
import com.example.demo.pojos.ItemCompra;
import com.example.demo.pojos.Producto;
import com.example.demo.repository.CompraDao;
import com.example.demo.repository.ItemCompraRepository;
import com.example.demo.repository.ProductoDao;
@Transactional
@Service
public class CompraService implements ICompraService {

	@Autowired
	private CompraDao dao;
	@Autowired
	private ProductoDao daoProducto;
	@Autowired
	private ItemCompraRepository daoArticulo;
	
	@Override
	public void guardarCompra(Compra c, Set<ItemCompra> articulos) {
		
		Compra compra = dao.crear(c);
		
		
		articulos.stream().forEach(p -> {
			p.getId().setIdCompra(compra);
			
			daoArticulo.save(p);
		});
		
		
		
	}

	@Override
	public List<Compra> listarComprasPorUsuario(long usuario) {
		
		ArrayList<Compra> lista = (ArrayList<Compra>) dao.findAllByUsuario(usuario);
		
		return lista;
	}

	@Override
	public void borrarUnArticulo(ItemCompra i) {
		
		daoArticulo.delete(i);
		
	}

	@Override
	public List<ItemCompra> listarArticulos(Compra c) {
		// TODO Auto-generated method stub
		return daoArticulo.findAllByCompra(c);
	}

	@Override
	public Compra compraPorId(Long id) {
		// TODO Auto-generated method stub
		return dao.buscar(id);
	}

}
