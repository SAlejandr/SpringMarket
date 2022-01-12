package com.example.demo.pojos;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario implements Serializable{
	//vairables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String contrasenna;
	@Column
	private String email;
	@Column
	private LocalDate fNacimiento;
	
	private BigInteger numeroTarjeta;
	private HashSet<Rol> roles;
	//contuctores
	public Usuario() {
		
		this(-1L ,"", "", "", "", LocalDate.now(), new HashSet<>());
	}
	public Usuario(long id, String nombre, String apellido, String contrasenna, String email, LocalDate fNacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenna = contrasenna;
		this.email = email;
		this.fNacimiento = fNacimiento;
	}

	public Usuario(String nombre, String apellido, String contrasenna, String email, LocalDate fNacimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenna = contrasenna;
		this.email = email;
		this.fNacimiento = fNacimiento;
	}

	public Usuario(long id, String nombre, String apellido, String contrasenna, String email, LocalDate fNacimiento,
			HashSet<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenna = contrasenna;
		this.email = email;
		this.fNacimiento = fNacimiento;
		this.roles = roles;
	}

	public Usuario(long id, String nombre, String apellido, String contrasenna, String email, LocalDate fNacimiento,
			BigInteger numeroTarjeta, HashSet<Rol> roles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenna = contrasenna;
		this.email = email;
		this.fNacimiento = fNacimiento;
		this.numeroTarjeta = numeroTarjeta;
		this.roles = roles;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(LocalDate fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	
	public BigInteger getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(BigInteger numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public HashSet<Rol> getRoles() {
		return roles;
	}
	public void setRoles(HashSet<Rol> roles) {
		this.roles = roles;
	}
	//equals y hascode
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
		Usuario other = (Usuario) obj;
		return id == other.id;
	}
	//To String
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasenna=" + contrasenna
				+ ", email=" + email + ", fNacimiento=" + fNacimiento + "]";
	}
	
	
}
