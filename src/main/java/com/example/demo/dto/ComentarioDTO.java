package com.example.demo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ComentarioDTO implements Serializable{
	
	private Long id, idPadre;
	
	private String email, texto, username;
	
	private LocalDate fecha;
	
	public ComentarioDTO() {
	}

	public ComentarioDTO(Long id, Long idPadre, String texto, String username, LocalDate fecha) {
		super();
		this.id = id;
		this.idPadre = idPadre;
		this.texto = texto;
		this.username = username;
		this.fecha = fecha;
	}

	public ComentarioDTO(Long id, Long idPadre, String email, String texto, String username, LocalDate fecha) {
		super();
		this.id = id;
		this.idPadre = idPadre;
		this.email = email;
		this.texto = texto;
		this.username = username;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		ComentarioDTO other = (ComentarioDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ComentarioDTO [id=" + id + ", idPadre=" + idPadre + ", email=" + email + ", texto=" + texto
				+ ", username=" + username + ", fecha=" + fecha + "]";
	}
	

}
