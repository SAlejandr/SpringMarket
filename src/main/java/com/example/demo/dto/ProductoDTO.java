package com.example.demo.dto;

import java.io.Serializable;
import java.util.Objects;

public class ProductoDTO implements Serializable {

	private long id;
	private String nombre;
	private int cantidad;
	private float precio;
	
	//Constructor
	public ProductoDTO() {
		this(0, "", 0, 0);
	}

	public ProductoDTO(long id, String nombre, int cantidad, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio=precio;
	}

	//sets y gets
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	//equals y hashCode
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
		ProductoDTO other = (ProductoDTO) obj;
		return id == other.id;
	}
	//To String
	@Override
	public String toString() {
		return "ProductoDTO [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}
	
}
