package com.example.demo.service;

import java.math.BigInteger;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojos.Tarjeta;
import com.example.demo.repository.TarjetaDao;
@Transactional
@Service
public class TarjetaService implements ITarjetaService {

	@Autowired
	private TarjetaDao dao;
	
	@Override
	public Tarjeta guardar(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return dao.crear(tarjeta);
	}

	@Override
	public List<Tarjeta> listarTodo() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Tarjeta buscarPorId(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		Optional<Tarjeta> optional = Optional.of(dao.buscar(tarjeta.getNumero()));
		
		return optional.orElse(new Tarjeta(BigInteger.ZERO,"",0,""));
	}

	@Override
	public Tarjeta borrarPorId(BigInteger tarjeta) {
		// TODO Auto-generated method stub
		
		Optional<Tarjeta> optional = Optional.of(dao.buscar(tarjeta));
		
		if(optional.isPresent()) {
			
			dao.borrar(optional.get());
		}
		
		return optional.orElse(new Tarjeta(BigInteger.ZERO,"",0,""));
	}
	
	@Override
	public Tarjeta actualizarTarjeta(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		Optional<Tarjeta> laTarjeta = Optional.of(dao.buscar(tarjeta.getNumero()));
		
		if(existeTarjeta(tarjeta.getNumero()))
			return dao.actualizar(tarjeta);
		else
			return dao.crear(tarjeta);		
	}

	@Override
	public boolean existeTarjeta(BigInteger tarjeta) {
		// TODO Auto-generated method stub
		Optional<Tarjeta> optional = Optional.of(dao.buscar(tarjeta));
		
		return optional.isPresent();
	}


}
