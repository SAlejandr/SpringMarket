package com.example.demo.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ComentarioDTO;
import com.example.demo.dto.ComentarioModificadoDTO;
import com.example.demo.dto.ComentarioNuevoDTO;
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

	private ComentarioDTO convertirComentario(Comentario c) {

		ComentarioDTO dto;

		if (c.getComentarioPadre() != null) {
			dto = new ComentarioDTO(c.getId(), c.getComentarioPadre().getId(), c.getTexto(), c.getUsuario().getNombre(),
					c.getFecha());
		} else {
			dto = new ComentarioDTO(c.getId(), null, c.getTexto(), c.getUsuario().getNombre(), c.getFecha());
		}

		return dto;

	}

	@GetMapping(value = "/todos")
	public List<ComentarioDTO> obtenerLista(@RequestParam Long producto) {

		Producto p = productoService.buscarPorId(producto);

		List<ComentarioDTO> comentarios = new ArrayList<ComentarioDTO>();

		service.obtenerComentariosPorProducto(p).stream().forEach(c -> comentarios.add(convertirComentario(c)));

		return comentarios;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<ComentarioDTO> postMethodName(@RequestBody ComentarioNuevoDTO comentarioDTO) {

		HttpStatus status;
		ResponseEntity<ComentarioDTO> response;

		Comentario comentario = new Comentario();

		if (comentarioDTO.getIdComentarioPadre() != null) {
			comentario.setComentarioPadre(service.buscarPorId(comentarioDTO.getIdComentarioPadre()).get());
		}

		comentario.setBorrado(false);
		comentario.setFecha(LocalDate.now());
		comentario.setTexto(comentarioDTO.getTexto());
		comentario.setProducto(productoService.buscarPorId(comentarioDTO.getIdProducto()));
		comentario.setUsuario(usuarioService.buscarPorId(comentarioDTO.getIdUsuario()));
		service.guardarComentario(comentario);
		status = HttpStatus.ACCEPTED;
		response = new ResponseEntity<ComentarioDTO>(convertirComentario(comentario), status);

		return response;
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<Comentario> borrarComentario(@PathVariable Long id) {

		ResponseEntity<Comentario> response;
		if (service.existenciaPorId(id)) {

			Comentario c = service.buscarPorId(id).get();
			c.setBorrado(true);
			service.guardarComentario(c);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {

			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return response;
	}
	@DeleteMapping(value = "/borrar/admin/{id}")
	public ResponseEntity<Comentario> eliminarComentario(@PathVariable Long id) {

		ResponseEntity<Comentario> response;
		if (service.existenciaPorId(id)) {

			service.borrarPorId(id);
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {

			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@PutMapping(value = "/actualizar")
	public ResponseEntity<ComentarioDTO> putMethodName(@RequestBody ComentarioModificadoDTO comentarioDTO) {

		HttpStatus status;
		ResponseEntity<ComentarioDTO> response;

		Comentario comentario = new Comentario();

		if (service.existenciaPorId(comentarioDTO.getId())) {

			comentario = service.buscarPorId(comentarioDTO.getId()).get();

			comentario.setTexto(comentarioDTO.getTexto());

			service.guardarComentario(comentario);

			status = HttpStatus.OK;
			response = new ResponseEntity<ComentarioDTO>(status);
		} else {

			status = HttpStatus.NOT_FOUND;
			response = new ResponseEntity<>(status);
		}

		return response;
	}

}
