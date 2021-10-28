package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.pojos.Usuario;

public interface UsuarioDao {

	public int save(Usuario usuario);
	public int count();
	public List<Usuario> findAll();
	public Optional<Usuario> findById(long id);
	public int delete(Usuario usuario);
	public Optional<Usuario> findByEmail(String email);
}
