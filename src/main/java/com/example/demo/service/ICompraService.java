package com.example.demo.service;

import java.util.List;

import com.example.demo.pojos.Compra;

public interface ICompraService {
	
	public void guardarCompra(Compra c);
	public List<Compra> listarComprasPorUsuario(long usuario);
	public void borrarUnArticulo(long idCompra, long articulo);

}
