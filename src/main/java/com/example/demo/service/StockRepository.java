package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ModeloMonedas;
import com.example.demo.entity.Proveedor;
import com.example.demo.entity.StockMuseo;

public interface StockRepository extends JpaRepository<StockMuseo, Integer>{
	StockMuseo findById(int id);
	List<StockMuseo> findByAnioAcuniacion(String anio);
	List<StockMuseo> findByModeloMonedas(ModeloMonedas modelo);
	List<StockMuseo> findByProveedor(Proveedor proveedor);
	
}
