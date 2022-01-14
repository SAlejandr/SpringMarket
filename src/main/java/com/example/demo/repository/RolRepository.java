package com.example.demo.repository;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Usuario;
@Repository
public class RolRepository extends DaoRepository<Rol> implements RolDao {

	/*@Autowired
	private JdbcTemplate jdbc;*/
	
	@Override
	public int saveAsignacion(byte rol, Usuario user) {
		// TODO Auto-generated method stub
		
		Query query = this.em.createNativeQuery("insert into usuario_rol (rol, usuario) value (:rol , :usuario)");
		query.setParameter("rol", rol);
		query.setParameter("usuario", user.getId());
		
		
		
		return query.executeUpdate();//jdbc.update("insert into usuario_rol(rol, usuario) value (?,?)", rol, usuario);
	}

	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		Query query = this.em.createQuery("FROM Rol");
		
		return query.getResultList();
	}

}
