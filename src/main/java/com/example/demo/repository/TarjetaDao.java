package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.example.demo.pojos.Tarjeta;

public interface TarjetaDao extends DaoGenerico<Tarjeta> {

	public List<Tarjeta> findAll();
	
}
