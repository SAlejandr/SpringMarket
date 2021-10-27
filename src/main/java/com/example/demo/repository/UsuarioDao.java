package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojos.Usuario;

public interface UsuarioDao {

	public int save(Usuario Usuario);
	public int count();
	public List<Usuario> findAll();
	public Optional<Usuario> findById(long id);
	public int delete(Usuario Usuario);
	public List<Usuario> findAllByTituloLike(String patronTitulo);
}
