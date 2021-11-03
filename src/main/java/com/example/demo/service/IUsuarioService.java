package com.example.demo.service;

import java.util.List;

import com.example.demo.pojos.Usuario;

public interface IUsuarioService {
	
	public int guardar(Usuario usuario);
	
	public List<Usuario> listarTodos();
	
	public Usuario buscarPorId(long id);
	
	public Usuario buscarPorEmail(String email);
	
	public Usuario borrarPorId(long id);
}

