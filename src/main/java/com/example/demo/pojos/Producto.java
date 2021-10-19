package com.example.demo.pojos;

import java.util.Objects;

public class Producto {

	//Variables
	private long id;
	private String titulo;
	private String descripcion;
	private float precio;
	private int descuento;
	//Constructores
	
	public Producto() {
		// TODO Auto-generated constructor stub
		this("", "", 0, 0);
		
	}

	public Producto(long id, String titulo, String descripcion, float precio, int descuento) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.descuento = descuento;
	}
	
	public Producto(String titulo, String descripcion, float precio, int descuento) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.descuento = descuento;
	}
	//Setter y getter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	//equals y hasCode

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
		Producto other = (Producto) obj;
		return id == other.id;
	}
	//toString

	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", descuento=" + descuento + "]";
	}
	
	
}
