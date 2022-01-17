package com.example.demo.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.dto.ProductoDTO;
@Entity
@Table
public class Compra implements Serializable {

	//variables
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "usuario")
	private Usuario usuario;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private LocalDateTime fecha;
	//constructor
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}

	public Compra(Usuario usuario, long id, LocalDateTime fecha) {
		super();
		this.usuario = usuario;
		this.id = id;
		this.fecha = fecha;
	}
	
	public Compra(long id, Usuario u) {
		
		this(u, id, LocalDateTime.now());

		
	}
	public Compra(Usuario usuario, LocalDateTime fecha) {
		this.usuario = usuario;
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

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
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
		return "Compra [usuario=" + usuario + ", id=" + id + ", fecha=" + fecha + "]";
	}
	
}
