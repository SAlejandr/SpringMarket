package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Rol;
@Repository
public class RolRepository implements RolDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public int saveAsignacion(byte rol, long usuario) {
		// TODO Auto-generated method stub
		return jdbc.update("insert into usuario_rol(rol, usuario) value (?,?)", rol, usuario);
	}

	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("select * from rol",(rs, rowNum) -> new Rol(rs.getByte("rol"), rs.getString("nombre")));
	}

}
