package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;

import com.example.demo.pojos.Tarjeta;

public interface ITarjetaService {

	public int guardar(Tarjeta tarjeta);
	public List<Tarjeta> listarTodo();
	public Tarjeta buscarPorId(Tarjeta tarjeta);
	public Tarjeta borrarPorId(BigInteger tarjeta);
	public int actualizarTarjeta(Tarjeta tarjeta);

}
