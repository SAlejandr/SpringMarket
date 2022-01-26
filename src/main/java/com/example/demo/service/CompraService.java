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
import com.example.demo.pojos.Usuario;
import com.example.demo.repository.CompraRepository;
import com.example.demo.repository.ElUsuarioRepository;
import com.example.demo.repository.ItemCompraRepository;
import com.example.demo.repository.ProductoDao;
import com.example.demo.repository.UsuarioDao;
import com.example.demo.repository.UsuarioRepository;
@Transactional
@Service
public class CompraService implements ICompraService {

	@Autowired
	private CompraRepository dao;
	@Autowired
	private ProductoDao daoProducto;
	@Autowired
	private ItemCompraRepository daoArticulo;
	@Autowired
	private ElUsuarioRepository daoUsuario;
	
	@Override
	public void guardarCompra(Compra c, Set<ItemCompra> articulos) {
		
		Compra compra = dao.save(c);
		
		
		articulos.stream().forEach(p -> {
			p.getId().setIdCompra(compra);
			
			daoArticulo.save(p);
		});
		
		
		
	}

	@Override
	public List<Compra> listarComprasPorUsuario(long usuario) {
		
		Optional<Usuario> u = daoUsuario.findById((Long)usuario);
		
		ArrayList<Compra> lista = (u.isPresent())? (ArrayList<Compra>) dao.findByUsuario(u.get()) : new ArrayList<>();
		
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
		return dao.findById(id).orElse(new Compra());
	}

	@Override
	public void todosVisibles() {
		// TODO Auto-generated method stub
		
		List<Compra> lista = dao.findAll();
		
		lista.stream().forEach(c -> c.setBorrado((Boolean)false));
		
	}
	
	
}
