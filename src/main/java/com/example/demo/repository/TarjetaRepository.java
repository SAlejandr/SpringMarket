package com.example.demo.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Tarjeta;

@Repository
public class TarjetaRepository implements TarjetaDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public int save(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return jdbc.update("insert into tarjeta(numero, titular, codSecure, dirFactura) value (?,?,?,?)",
				tarjeta.getNumero(), tarjeta.getTitular(), tarjeta.getCodSeguridad(), tarjeta.getDirFactura());
	}
	@Override
	public int update(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return jdbc.update("update tarjeta set titular = ?, codSecure = ?, dirFactura =? where numeroTarjeta=?",
				tarjeta.getTitular(),  tarjeta.getCodSeguridad(), tarjeta.getDirFactura(),tarjeta.getNumero());
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		Integer cantidad = jdbc.queryForObject("select count(*) as 'cantidad' from tarjeta", Integer.class);
		return cantidad;
	}

	@Override
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("select * from tarjeta", (rs, rowNum) -> new Tarjeta(new BigInteger(rs.getString("numero")), rs.getString("titular"), rs.getInt("codSecure"), rs.getString("dirFactura")));
	}

	@Override
	public Optional<Tarjeta> findById(BigInteger numeroTarjeta) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select * from tarjeta where numero = ?",(rs, rowNum) -> Optional.of( new Tarjeta(new BigInteger(rs.getString("numero")), rs.getString("titular"), rs.getInt("codSecure"), rs.getString("dirFactura"))) , numeroTarjeta);
	}

	@Override
	public int deleteById(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		return jdbc.update("delete from tarjeta where numero = ?", tarjeta.getNumero());
	}

}
