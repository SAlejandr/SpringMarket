package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pojos.Producto;
import com.example.demo.repository.ProductoDao;

@Controller
public class MainController {
	
	@Autowired
	private ProductoDao dao;
	
	@GetMapping(value = "/indesx")
	public String index(Model modelo) {
		
		ArrayList<Producto> lista = (ArrayList<Producto>) dao.findAll();
		ArrayList<Producto> productos = new ArrayList<>();
		
		for (int i = 0; i < lista.size() && i < 8; i++) {
			
			productos.add(lista.get(i));
		}
		
		modelo.addAttribute("productos", productos);
		
		return "index";
	}
	
	@GetMapping(value = "/formula")
	public String guardarProd(Model modelo) {
		
		return "formulario";
	}

	
	@PostMapping(value = "/formula")
	public String guardarProducto(Model modelo, @RequestParam String nombre, @RequestParam String descripcion, 
			@RequestParam float precio, @RequestParam int descuento ) {
		
		Producto producto = new Producto(nombre, descripcion, precio, descuento);
		
		dao.save(producto);
		
		return "redirect:/formula";
	}

	
	@GetMapping(value = "/producto/{id}")
	public String buscarProductoPorId(Model modelo, @PathVariable long id) {
		
		Optional<Producto> optional = dao.findById(id);
		if(optional.isPresent()) {
			modelo.addAttribute("producto", optional.get());
			return "mostrar";
		}else {
			
			return "mostrarNull";
		}
			
		
	}
//iansboi  32
	@GetMapping(value = "/producto/borrar/{id}")
	public String borrar(@PathVariable long id) {
		
		Optional<Producto> optional = dao.findById(id);
		
		if(optional.isPresent()) {
			
			dao.delete(optional.get());
			
			return "redirect:/indesx";
		}else {
			
			return "redirect:/producto/"+id;
		}
		
		
	}

	@GetMapping(value = "/producto/buscar")
	public String buscarPorPatronDeTitulo(Model modelo,@RequestParam String patron) {
		
		ArrayList<Producto> productos = (ArrayList<Producto>) dao.findAllByTituloLike(patron);
		
		if(productos.isEmpty()) {
			return "buscarNull";
		}else {
		modelo.addAttribute("productos", productos);
		
		return "buscar";	
		}

	}
}
