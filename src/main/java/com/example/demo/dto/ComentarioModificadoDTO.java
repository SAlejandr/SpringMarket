package com.example.demo.dto;

import java.io.Serializable;
import java.util.Objects;

public class ComentarioModificadoDTO implements Serializable{
	
	private Long id;
	
	private String texto;
	
	public ComentarioModificadoDTO() {
	}

	public ComentarioModificadoDTO(Long id, String texto) {
		super();
		this.id = id;
		this.texto = texto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentarioModificadoDTO other = (ComentarioModificadoDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return "ComentarioModificadoDTO [id=" + id + ", texto=" + texto + "]";
	}
	

}
