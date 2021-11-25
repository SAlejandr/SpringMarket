package com.example.demo.repository;

import java.util.List;

import com.example.demo.pojos.Rol;

public interface RolDao {

	public int saveAsignacion(byte rol, long usuario);
	public List<Rol> findAll();
}
