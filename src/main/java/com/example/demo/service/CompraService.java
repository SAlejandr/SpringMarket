package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.pojos.Compra;
import com.example.demo.pojos.IdItemCompra;
import com.example.demo.pojos.Producto;
import com.example.demo.repository.CompraDao;
import com.example.demo.repository.ItemCompraDao;
import com.example.demo.repository.ProductoDao;
@Transactional
@Service
public class CompraService implements ICompraService {

	@Autowired
	private CompraDao dao;
	@Autowired
	private ProductoDao daoProducto;
	@Autowired
	private ItemCompraDao daoArticulo;
	
	@Override
	public void guardarCompra(Compra c) {
		
		dao.crear(c);
		
		c.getProductos().stream().forEach(p -> {
			
			
			daoArticulo.saveArticle(c.getId(), p);
			});
		
	}

	@Override
	public List<Compra> listarComprasPorUsuario(long usuario) {
		
		ArrayList<Compra> lista = (ArrayList<Compra>) dao.findAllByUsuario(usuario);
		
		lista.stream().forEach(c -> {
			Set<ProductoDTO> set = dao.findListaById(c.getId());
			
			c.setProductos((HashSet<ProductoDTO>) set);
		});
		
		
		lista.stream().forEach(c -> {
			c.getProductos().stream().forEach(p ->{
				
				Producto pro = daoProducto.buscar(p.getId()).get();
				
				p.setNombre(pro.getTitulo());
			});
		});
		
		
		return lista;
	}

	@Override
	public void borrarUnArticulo(long idCompra, long articulo) {

		dao.deleteByProducto(idCompra, articulo);
		
	}

}
