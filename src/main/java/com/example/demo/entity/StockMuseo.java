package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "STOCK_MUSEO")
public class StockMuseo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date anioAcuniacion;
	private String ciudadAcuniacion;
	private Date fechaAdquisicion;
	private String estadoConservacion;
	@OneToOne
	private ModeloMonedas modeloMonedas;	
	@OneToOne
	private Proveedor proveedor;
	
	
	
	public StockMuseo() {
		
	}
	public StockMuseo(Date anioAcuniacion, String ciudadAcuniacion, Date fechaAdquisicion,
			String estadoConservacion, Proveedor proveedor,ModeloMonedas moneda) {
		this.anioAcuniacion = anioAcuniacion;
		this.ciudadAcuniacion = ciudadAcuniacion;
		this.fechaAdquisicion = fechaAdquisicion;
		this.estadoConservacion = estadoConservacion;
		this.proveedor = proveedor;
		this.modeloMonedas = moneda;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getAnioAcuniacion() {
		return anioAcuniacion;
	}
	public void setAnioAcuniacion(Date anioAcuniacion) {
		this.anioAcuniacion = anioAcuniacion;
	}
	public String getCiudadAcuniacion() {
		return ciudadAcuniacion;
	}
	public void setCiudadAcuniacion(String ciudadAcuniacion) {
		this.ciudadAcuniacion = ciudadAcuniacion;
	}
	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}
	public String getEstadoConservacion() {
		return estadoConservacion;
	}
	public void setEstadoConservacion(String estadoConservacion) {
		this.estadoConservacion = estadoConservacion;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public ModeloMonedas getModeloMonedas() {
		return modeloMonedas;
	}
	public void setModeloMonedas(ModeloMonedas modeloMonedas) {
		this.modeloMonedas = modeloMonedas;
	}
	

}
