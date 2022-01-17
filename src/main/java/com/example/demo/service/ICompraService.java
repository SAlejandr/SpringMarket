package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.pojos.Compra;
import com.example.demo.pojos.ItemCompra;

public interface ICompraService {
	
	public void guardarCompra(Compra c, Set<ItemCompra> articulos);
	public List<Compra> listarComprasPorUsuario(long usuario);
	public void borrarUnArticulo(long idCompra, long articulo);

}
