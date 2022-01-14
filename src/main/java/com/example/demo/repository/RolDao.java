package com.example.demo.repository;

import java.util.List;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Usuario;

public interface RolDao extends DaoGenerico<Rol>{

	public int saveAsignacion(byte rol, Usuario user);
	public List<Rol> findAll();
}
