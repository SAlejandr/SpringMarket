package com.example.demo.pojos;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "listaCompra")
public class ItemCompra {
	
	@EmbeddedId
	private IdItemCompra id;
	
	@Column
	private int cantidad;

	public ItemCompra() {
		// TODO Auto-generated constructor stub
		
		this.id = new IdItemCompra();
		this.cantidad = 0;
	}

	public ItemCompra(IdItemCompra id, int cantidad) {
		super();
		this.id = id;
		this.cantidad = cantidad;
	}

	public IdItemCompra getId() {
		return id;
	}

	public void setId(IdItemCompra id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCompra other = (ItemCompra) obj;
		return cantidad == other.cantidad && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ItemCompra [id=" + id + ", cantidad=" + cantidad + "]";
	}
	
	
	
}
