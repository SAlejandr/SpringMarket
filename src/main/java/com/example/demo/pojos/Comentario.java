package com.example.demo.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario implements Serializable {

	/*Variables*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	private Producto producto;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "respuestaDe")
	private Comentario comentarioPadre;
	
	/*Constructor*/
	public Comentario() {
	}

	public Comentario(Long id, Usuario usuario, Producto producto, Comentario comentarioPadre) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.producto = producto;
		this.comentarioPadre = comentarioPadre;
	}

	/*Sets y gets*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Comentario getComentarioPadre() {
		return comentarioPadre;
	}

	public void setComentarioPadre(Comentario comentarioPadre) {
		this.comentarioPadre = comentarioPadre;
	}

	/*Equals y hashCode*/
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
		Comentario other = (Comentario) obj;
		return Objects.equals(id, other.id);
	}
	/*To String*/
	@Override
	public String toString() {
		return "Comentario [id=" + id + ", usuario=" + usuario + ", producto=" + producto + ", comentarioPadre="
				+ comentarioPadre + "]";
	}
	
	
}
