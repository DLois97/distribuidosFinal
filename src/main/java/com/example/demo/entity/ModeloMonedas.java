package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "MODELO_MONEDAS")
public class ModeloMonedas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String valor_facial;
	private String unidad_monetaria;
	private double diametro;
	private double peso;
	private String metal;
	private String descripcion;
	
	
	
	public ModeloMonedas() {
	
	}

	public ModeloMonedas(String valor_facial, String unidad_monetaria, double diametro, double peso, String metal,
			String descripcion) {
		this.valor_facial = valor_facial;
		this.unidad_monetaria = unidad_monetaria;
		this.diametro = diametro;
		this.peso = peso;
		this.metal = metal;
		this.descripcion = descripcion;
	}
	
	public String getvalor_facial() {
		return valor_facial;
	}

	public void setvalor_facial(String valor_facial) {
		this.valor_facial = valor_facial;
	}

	public String getunidad_monetaria() {
		return unidad_monetaria;
	}

	public void setunidad_monetaria(String unidad_monetaria) {
		this.unidad_monetaria = unidad_monetaria;
	}

	public double getDiametro() {
		return diametro;
	}

	public void setDiametro(double diametro) {
		this.diametro = diametro;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getMetal() {
		return metal;
	}

	public void setMetal(String metal) {
		this.metal = metal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
