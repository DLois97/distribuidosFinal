package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROVEEDOR")
public class Proveedor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idInterno;
	@Column(unique=true)
	private long id;
	private String nombre;
	private int codigo;
	private String email;
	private String telefono;
	
	
	public Proveedor() {
		
	}
	
	public Proveedor(long id, String nombre, int cp, String email, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.codigo = cp;
		this.email = email;
		this.telefono = telefono;
	}
	
	public long getIdInterno() {
		return idInterno;
	}

	public void setIdInterno(long idInterno) {
		this.idInterno = idInterno;
	}

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
	public int getCP() {
		return this.codigo;
	}
	public void setCP(int cp) {
		this.codigo = cp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
