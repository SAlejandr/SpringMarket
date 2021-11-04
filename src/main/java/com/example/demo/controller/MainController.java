package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pojos.Producto;
import com.example.demo.repository.ProductoDao;
import com.example.demo.service.IProductoService;

@Controller
public class MainController {

	@Autowired
	private IProductoService productoService;

	@GetMapping(value = "/indesx")
	public String index(Model modelo, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		
		
		ArrayList<Producto> lista = productoService.listarTodas();
		ArrayList<Producto> productos = new ArrayList<>();

		for (int i = 0; i < lista.size() && i < 8; i++) {

			productos.add(lista.get(i));
		}

		modelo.addAttribute("productos", productos);
		modelo.addAttribute("logueado", logueado);
		
		return "index";
	}

	@GetMapping(value = "/formula")
	public String guardarProd(Model modelo, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		modelo.addAttribute("logueado", logueado);
		return "formulario";
	}

	@PostMapping(value = "/formula")
	public String guardarProducto(Model modelo, @RequestParam String nombre, @RequestParam String descripcion,
			@RequestParam float precio, @RequestParam int descuento, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}

		Producto producto = new Producto(nombre, descripcion, precio, descuento);

		productoService.guardar(producto);
		modelo.addAttribute("logueado", logueado);
		return "redirect:/formula";
	}

	@GetMapping(value = "/producto/{id}")
	public String buscarProductoPorId(Model modelo, @PathVariable long id, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}

		Producto p = productoService.buscarPorId(id);
		modelo.addAttribute("logueado", logueado);
		if (p.getId() != -1) {
			modelo.addAttribute("producto", p);
			return "mostrar";
		} else {

			return "mostrarNull";
		}

	}

//iansboi  32
	@GetMapping(value = "/producto/borrar/{id}")
	public String borrar(Model modelo,@PathVariable long id, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}

		Producto p = productoService.borrarPorId(id);
		modelo.addAttribute("logueado", logueado);
		if (p.getId() != -1) {

			return "redirect:/indesx";
		} else {

			return "redirect:/producto/" + id;
		}

	}

	@GetMapping(value = "/producto/buscar")
	public String buscarPorPatronDeTitulo(Model modelo, @RequestParam String patron, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}

		ArrayList<Producto> productos = productoService.buscarPorPatronDeTitulo(patron);
		modelo.addAttribute("logueado", logueado);
		if (productos.isEmpty() || patron.equals("")) {
			return "buscarNull";
		} else {
			modelo.addAttribute("productos", productos);

			return "buscar";
		}

	}
}
