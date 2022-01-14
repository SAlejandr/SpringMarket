package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Tarjeta;

@Repository
public class TarjetaRepository extends DaoRepository<Tarjeta> implements TarjetaDao {

	@Override
	public List<Tarjeta> findAll() {
		
		Query query = this.em.createQuery("FROM Tarjeta");
		
		return query.getResultList();	}

	

}
