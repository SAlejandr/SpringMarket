package com.example.demo.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class IdItemCompra implements Serializable{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "id")
	private Compra idCompra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "articulo")
	private Producto idProducto;
	
	public IdItemCompra() {
		// TODO Auto-generated constructor stub
		
		this.idCompra = new Compra();
		this.idProducto = new Producto();
	}

	public IdItemCompra(Compra idCompra, Producto idProducto) {
		super();
		this.idCompra = idCompra;
		this.idProducto = idProducto;
	}

	public Compra getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Compra idCompra) {
		this.idCompra = idCompra;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCompra, idProducto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdItemCompra other = (IdItemCompra) obj;
		return Objects.equals(idCompra, other.idCompra) && Objects.equals(idProducto, other.idProducto);
	}

	@Override
	public String toString() {
		return "IdItemCompra [idCompra=" + idCompra + ", idProducto=" + idProducto + "]";
	}
	
	
	
	
}
