package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.pojos.Tarjeta;
import com.example.demo.repository.TarjetaRepository;

@Transactional
@Service
public class TarjetaService implements ITarjetaService {

	@Autowired
	private TarjetaRepository dao;

	@Override
	public Tarjeta guardar(Tarjeta tarjeta) {
		return dao.save(tarjeta);
	}

	@Override
	public List<Tarjeta> listarTodo() {
		return dao.findAll();
	}

	@Override
	public Tarjeta buscarPorId(Tarjeta tarjeta) {
		Optional<Tarjeta> optional = dao.findById(tarjeta.getNumero());

		return optional.orElse(new Tarjeta(BigInteger.ZERO, "", 0, ""));
	}

	@Override
	public Tarjeta borrarPorId(BigInteger tarjeta) {

		Optional<Tarjeta> optional = dao.findById(tarjeta);

		if (optional.isPresent()) {

			dao.delete(optional.get());
		}

		return optional.orElse(new Tarjeta(BigInteger.ZERO, "", 0, ""));
	}

	@Override
	public Tarjeta actualizarTarjeta(Tarjeta tarjeta) {
		return dao.save(tarjeta);

	}

	@Override
	public boolean existeTarjeta(BigInteger tarjeta) {
		Optional<Tarjeta> optional = dao.findById(tarjeta);

		return optional.isPresent();
	}

}
