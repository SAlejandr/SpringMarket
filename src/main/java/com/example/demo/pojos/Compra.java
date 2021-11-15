package com.example.demo.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

import com.example.demo.dto.ProductoDTO;

public class Compra implements Serializable {

	//variables
	
	private Usuario usuario;
	private long id;
	private HashSet<ProductoDTO> productos;
	private LocalDate fecha;
	//constructor
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Compra(Usuario usuario, long id, HashSet<ProductoDTO> productos, LocalDate fecha) {
		super();
		this.usuario = usuario;
		this.id = id;
		this.productos = productos;
		this.fecha = fecha;
	}
	//setter y getter

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HashSet<ProductoDTO> getProductos() {
		return productos;
	}

	public void setProductos(HashSet<ProductoDTO> productos) {
		this.productos = productos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Compra [usuario=" + usuario + ", id=" + id + ", productos=" + productos + ", fecha=" + fecha + "]";
	}
	
}
