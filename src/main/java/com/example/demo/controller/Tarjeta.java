package com.example.demo.controller;

import java.math.BigInteger;
import java.util.Objects;

public class Tarjeta {
	//variables
	private BigInteger numero;
	private String titular;
	private int codSeguridad;
	private String dirFactura;
	//constructores a
	public Tarjeta() {
		// TODO Auto-generated constructor stub
		this(new BigInteger("0"),"",0,"");
	}

	public Tarjeta(BigInteger numero, String titular, int codSeguridad, String dirFactura) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.codSeguridad = codSeguridad;
		this.dirFactura = dirFactura;
	}
	//setter y Getter
	public BigInteger getNumero() {
		return numero;
	}

	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getCodSeguridad() {
		return codSeguridad;
	}

	public void setCodSeguridad(int codSeguridad) {
		this.codSeguridad = codSeguridad;
	}

	public String getDirFactura() {
		return dirFactura;
	}

	public void setDirFactura(String dirFactura) {
		this.dirFactura = dirFactura;
	}
	//equals y hashCode
	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return Objects.equals(numero, other.numero);
	}
	//toString
	@Override
	public String toString() {
		return "Tarjeta [numero=" + numero + ", titular=" + titular + ", codSeguridad=" + codSeguridad + ", dirFactura="
				+ dirFactura + "]";
	}
	
	
}
