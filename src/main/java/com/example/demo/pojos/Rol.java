package com.example.demo.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Rol implements Serializable{
	
	
	private byte id;
	private String nombre;
	
	public Rol() {
		this((byte) 0,"");
	}

	public Rol(byte id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		Rol other = (Rol) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}

}
