package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.pojos.Comentario;
import com.example.demo.pojos.Producto;
import com.example.demo.pojos.Usuario;
import com.example.demo.service.IComentarioService;
import com.example.demo.service.IProductoService;
import com.example.demo.service.IUsuarioService;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {
	
	@Autowired
	private IComentarioService service;
	@Autowired
	private IProductoService productoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = "/todos")
	public List<ComentarioDTO> obtenerLista(@RequestParam Long producto) {
		
		Producto p = productoService.buscarPorId(producto);
		
		List<ComentarioDTO> comentarios = new ArrayList<ComentarioDTO>();
		
		TreeMap<Long, Usuario> usuarios = new TreeMap<>();
		
		usuarioService.listarTodos().stream().forEach(u -> usuarios.put(u.getId(), u));
		
		service.obtenerComentariosPorProducto(p).stream().forEach(c -> {
			
			if(c.getComentarioPadre() != null) {
				comentarios.add(new ComentarioDTO(c.getId(), c.getComentarioPadre().getId(), c.getTexto(), usuarios.get(c.getUsuario().getId()).getNombre(), c.getFecha()));
			}else {
				comentarios.add(new ComentarioDTO(c.getId(), null, c.getTexto(), usuarios.get(c.getUsuario().getId()).getNombre(), c.getFecha()));
			}
			
		});
		
		return comentarios;
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

	
	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<Comentario> deleteMethodName(@PathVariable Long id) {

		ResponseEntity<Comentario> response;
		if(service.existenciaPorId(id)) {
			
			Comentario c = service.buscarPorId(id).get();
			c.setBorrado(true);
			service.guardarComentario(c);
			response = new ResponseEntity<>(HttpStatus.OK);
		}else {
			
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}


}
