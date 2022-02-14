package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojos.Comentario;
import com.example.demo.pojos.Producto;
import com.example.demo.service.IComentarioService;
import com.example.demo.service.IProductoService;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
	
	@Autowired
	private IComentarioService service;
	@Autowired
	private IProductoService productoService;
	
	@GetMapping(value = "/obtenerLista")
	public List<Comentario> obtenerLista(@RequestParam Long producto) {
		
		Producto p = productoService.buscarPorId(producto);
		
		
		return service.obtenerComentariosPorProducto(p);
	}

	@PostMapping(value = "/add")
	public ResponseEntity<Comentario> postMethodName(@RequestBody Comentario comentario) {
		
		HttpStatus status;
		ResponseEntity<Comentario> response;
		
		if(service.existenciaPorId(comentario.getId())) {
			status = HttpStatus.BAD_REQUEST;
			response = new ResponseEntity<>(status);
			
		}else {
			
			status = HttpStatus.ACCEPTED;
			response = new ResponseEntity<Comentario>(service.guardarComentario(comentario), status);
		}
		
		return response;
	}


}
