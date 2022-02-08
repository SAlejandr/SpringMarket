package com.example.demo.service;

import java.util.List;

import com.example.demo.pojos.Imagen;

public interface IImagenService {

	public Imagen encontrarImagen(Long id);
	public List<Imagen> listaImagenes();
	public Imagen guardarImagen(Imagen imagen);
	//
}
