package com.example.demo.service;

import java.math.BigInteger;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojos.Tarjeta;
import com.example.demo.repository.TarjetaDao;

@Service
public class TarjetaService implements ITarjetaService {

	@Autowired
	private TarjetaDao dao;
	
	@Override
	public int guardar(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return dao.save(tarjeta);
	}

	@Override
	public List<Tarjeta> listarTodo() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Tarjeta buscarPorId(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		Optional<Tarjeta> optional = dao.findById(tarjeta.getNumero());
		
		return optional.orElse(new Tarjeta(BigInteger.ZERO,"",0,""));
	}

	@Override
	public Tarjeta borrarPorId(BigInteger tarjeta) {
		// TODO Auto-generated method stub
		
		Optional<Tarjeta> optional = dao.findById(tarjeta);
		
		if(optional.isPresent()) {
			
			dao.deleteById(optional.get());
		}
		
		return optional.orElse(new Tarjeta(BigInteger.ZERO,"",0,""));
	}

}
