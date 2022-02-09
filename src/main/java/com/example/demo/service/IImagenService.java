package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.pojos.Imagen;

public interface IImagenService {

	public Imagen encontrarImagen(Long id);
	public List<Imagen> listaImagenes();
	public Imagen guardarImagen(Imagen imagen);
	public Imagen actualizarImagen(Long id, MultipartFile file);
	//
}
