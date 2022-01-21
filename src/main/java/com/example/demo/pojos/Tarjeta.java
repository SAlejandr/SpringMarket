package com.example.demo.pojos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Tarjeta implements Serializable{
	//variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private BigInteger numero;
	@Column
	private String titular;
	@Column(name = "codigo_seguridad")
	private int codSeguridad;
	@Column(name= "facturacion")
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
