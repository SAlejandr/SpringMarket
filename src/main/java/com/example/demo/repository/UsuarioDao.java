package com.example.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Tarjeta;
import com.example.demo.pojos.Usuario;

public interface UsuarioDao extends DaoGenerico<Usuario>{

	public List<Usuario> findAll();
	public Optional<Usuario> findByEmail(String email);
	public int updateTarjeta(long id, Tarjeta tarjeta);
	public Set<Rol> findAllRolByUser(long id);
}
