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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pojos.Imagen;
import com.example.demo.pojos.Producto;
import com.example.demo.service.IImagenService;
import com.example.demo.service.IProductoService;

@Controller
public class MainController {

	@Autowired
	private IProductoService productoService;
	@Autowired
	private IImagenService imgServicio;

	@GetMapping(value = {"/indesx", "/","/index"})
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
		
		session.setAttribute("idProducto",producto.getId());
		return "redirect:/addImagen";
	}
	
	@GetMapping(value = "/addImagen")
	public String guardarImagen(Model modelo, HttpSession session) {

		Boolean logueado = (Boolean) session.getAttribute("logueado");
		if(logueado == null) {
			
			logueado = false;
		}
		modelo.addAttribute("logueado", logueado);
		return "addImagen";
	}
	
	@PostMapping("/cargar")
	public String fileUpload(@RequestParam("file") MultipartFile file, HttpSession session,
			@RequestParam("idProducto") long idProducto) {
		try {
			byte[] image = file.getBytes();
			Imagen img = new Imagen("foto", image);
			Imagen saveImage = imgServicio.actualizarImagen(idProducto, file);
			if (saveImage.getId()!=0L) {
				return "redirect:/profesor/perfil/" + idProducto;
			} else {
				return "redirect:/imagenes/cargar/" + idProducto;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/imagenes/cargar/" + idProducto;
		}
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
