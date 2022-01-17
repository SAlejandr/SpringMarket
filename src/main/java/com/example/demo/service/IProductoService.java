package com.example.demo.service;

import java.util.*;

import com.example.demo.pojos.Producto;

public interface IProductoService {

	public Producto guardar(Producto producto);
	
	public ArrayList<Producto> listarTodas();
	
	public Producto buscarPorId(long id);
	
	public ArrayList<Producto> buscarPorPatronDeTitulo(String patron);
	
	public Producto borrarPorId(long id);
}
