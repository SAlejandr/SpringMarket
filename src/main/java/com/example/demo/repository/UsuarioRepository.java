package com.example.demo.repository;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Rol;
import com.example.demo.pojos.Tarjeta;
import com.example.demo.pojos.Usuario;

@Repository
public class UsuarioRepository extends DaoRepository<Usuario> implements UsuarioDao{

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
				Query query = this.em.createQuery("FROM Usuario");
				
				return query.getResultList();
				
	}



	@Override
	public Optional<Usuario> findByEmail(String email) {
		Query query = this.em.createQuery("FROM Usuario u where u.email = :email");
		query.setParameter("email", email);
		
		Usuario optional =  (Usuario)query.getSingleResult();
		
		return Optional.of(optional);
		
	}

	@Override
	public int updateTarjeta(long id, Tarjeta tarjeta) {
		//aaaaaaaaaaaaaaaaaa
		Query query = this.em.createQuery("Update Usuario u SET u.tarjeta = :tarjeta where u.id = :id");
		query.setParameter("id", id);
		query.setParameter("tarjeta", tarjeta);
		
		
		return query.executeUpdate();
	}

	@Override
	public List<Rol> findAllRolByUser(long id) {
		
		Query query = this.em.createQuery("FROM Usuario u where u.id = :id");
		query.setParameter("id", id);
		Usuario u = (Usuario) query.getSingleResult();
		
		return u.getRoles();
		
	}

	
	
}
