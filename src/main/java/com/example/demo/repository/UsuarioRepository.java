package com.example.demo.repository;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Usuario;

@Repository
public class UsuarioRepository implements UsuarioDao{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public int save(Usuario usuario) {
		// TODO Auto-generated method stub
		return jdbc.update("insert into usuario(nombre, apellido, contrasenna, email, fNacimiento) values (?,?,?,?, ?);", 
				usuario.getNombre(), usuario.getApellido(), usuario.getContrasenna(), usuario.getEmail(), Date.valueOf(usuario.getfNacimiento()));
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		Integer cantidad = jdbc.queryForObject("select count(*) as 'cantidad' from usuario", Integer.class);
		return cantidad;
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("select * from usuario", (rs, rowNum) -> new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasenna"), rs.getString("email"), rs.getDate("fNacimiento").toLocalDate()));
	}

	@Override
	public Optional<Usuario> findById(long id) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select * from usuario where id = ?", (rs, rowNum) -> Optional.of(new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasenna"), rs.getString("email"), rs.getDate("fNacimiento").toLocalDate())),
				id);
	}

	@Override
	public int delete(Usuario usuario) {
		// TODO Auto-generated method stub
		return jdbc.update("delete from usuario where id = ?", usuario.getId());
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		// TODO Auto-generated method stub
		try {
	  return jdbc.queryForObject("select * from usuario where email = ?", (rs, rowNum) -> Optional.of(new Usuario(rs.getLong("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasenna"), rs.getString("email"), rs.getDate("fNacimiento").toLocalDate())),
				email);
		}catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
		
	}

	@Override
	public int updateTarjeta(long id, BigInteger numero) {
		// TODO Auto-generated method stub
		//aaaaaaaaaaaaaaaaaa
		return jdbc.update("UPDATE usuario SET numeroTarjeta = ? WHERE id = ?", numero, id);
	}

	@Override
	public List<Rol> findAllRolByUser(long id) {
		// TODO Auto-generated method stub
		return jdbc.query("select * from usuario_rol where usuario = ?", (rs, rowNum) -> new Rol(rs.getByte("id"), rs.getString("nombre")), id);
	}

	
	
}
