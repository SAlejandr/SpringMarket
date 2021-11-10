package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.example.demo.pojos.Tarjeta;

public interface TarjetaDao {

	public int save(Tarjeta tarjeta);
	public int update(Tarjeta tarjeta);
	public int count();
	public List<Tarjeta> findAll();
	public Optional<Tarjeta> findById(BigInteger numeroTarjeta);
	public int deleteById(Tarjeta tarjeta);
	
}
