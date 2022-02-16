package com.example.demo.dto;

import java.util.Objects;

public class ComentarioNuevoDTO {
	
	private Long idUsuario, idProducto, idComentarioPadre;
	private String texto;
	
	public ComentarioNuevoDTO() {
	}

	public ComentarioNuevoDTO(Long idUsuario, Long idProducto, Long idComentarioPadre, String texto) {
		super();
		this.idUsuario = idUsuario;
		this.idProducto = idProducto;
		this.idComentarioPadre = idComentarioPadre;
		this.texto = texto;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdComentarioPadre() {
		return idComentarioPadre;
	}

	public void setIdComentarioPadre(Long idComentarioPadre) {
		this.idComentarioPadre = idComentarioPadre;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComentarioPadre, idProducto, idUsuario, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentarioNuevoDTO other = (ComentarioNuevoDTO) obj;
		return Objects.equals(idComentarioPadre, other.idComentarioPadre)
				&& Objects.equals(idProducto, other.idProducto) && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return "ComentarioDTO [idUsuario=" + idUsuario + ", idProducto=" + idProducto + ", idComentarioPadre="
				+ idComentarioPadre + ", texto=" + texto + "]";
	}
	
	

}
