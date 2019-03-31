package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor,String> {
	Proveedor findById(long  id);
	Proveedor findByNombre(String nombre);
	Proveedor findByTelefono(String telefono);
	List<Proveedor> findByCodigo(int codigo);
	List<Proveedor> findAll();
	
}
